package com.example.notes.domain.use_case.delete_note

import android.util.Log
import com.example.notes.common.NetworkResponse
import com.example.notes.domain.model.NoteModel
import com.example.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: NotesRepository) {
    operator fun invoke(noteId: String): Flow<NetworkResponse<NoteModel>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val response = repository.deleteNote(noteId = noteId)
            if (response!!.isSuccessful && response.body() != null) {

                Log.d("Response", response.body().toString())

                emit(NetworkResponse.Success(data = response.body()))

            } else if (response.errorBody() != null) {
                try {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(NetworkResponse.Error(jsonObj.getString("message").toString()))
                } catch (e: Exception) {
                    emit(NetworkResponse.Error("Something went wrong."))
                }

            } else {
                emit(NetworkResponse.Error("Something went wrong"))
            }
        } catch (e: HttpException) {
            emit(NetworkResponse.Error(e.localizedMessage ?: "An unexpected error occurred."))

        } catch (e: IOException) {
            emit(NetworkResponse.Error("Check your internet connection"))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }
}