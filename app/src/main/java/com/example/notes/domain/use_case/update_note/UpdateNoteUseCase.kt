package com.example.notes.domain.use_case.update_note

import com.example.notes.common.NetworkResponse
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.domain.model.Note
import com.example.notes.domain.model.NoteModel
import com.example.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    operator fun invoke(body: UpdateNoteDto, noteId: String): Flow<NetworkResponse<NoteModel>> =
        flow {
            try {
                emit(NetworkResponse.Loading())
                val response: Response<NoteModel>? =
                    notesRepository.updateNote(body = body, noteId = noteId)
                if (response!!.isSuccessful && response.body() != null) {
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
            }
        }

}