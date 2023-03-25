package com.example.notes.domain.use_case.notes

import com.example.notes.common.NetworkResponse
import com.example.notes.domain.model.Note
import com.example.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class NotesUseCase @Inject constructor(private val repository: NotesRepository) {
    operator fun invoke(): Flow<NetworkResponse<List<Note>>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val response = repository.getNotes()

            if (response?.body() != null && response.isSuccessful) {
                emit(NetworkResponse.Success(response.body()))
            } else if (response?.errorBody() != null) {
                try {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(NetworkResponse.Error(jsonObj.getString("message").toString()))
                } catch (e: Exception) {
                    emit(NetworkResponse.Error("Something went wrong."))
                }
            } else {
                emit(NetworkResponse.Error("Something went wrong."))
            }

        } catch (e: HttpException) {
            emit(NetworkResponse.Error(e.message()))
        } catch (e: IOException) {
            emit(NetworkResponse.Error(e.localizedMessage))
        }
    }

}