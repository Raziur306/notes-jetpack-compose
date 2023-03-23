package com.example.notes.data.repository

import com.example.notes.data.remote.NotesApi
import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.data.repository.model.CreatedNoteModel
import com.example.notes.data.repository.model.DeletedNoteModel
import com.example.notes.data.repository.model.NotesModel
import com.example.notes.data.repository.model.UpdatedNoteModel
import com.example.notes.domain.repository.NotesRepository
import retrofit2.Response

import javax.inject.Inject

class NotesRepositoryImp @Inject constructor(private val api: NotesApi) : NotesRepository {
    override suspend fun getNotes(): Response<NotesModel> {
        return api.getNotes()
    }

    override suspend fun createNote(body: CreateNoteDto): Response<CreatedNoteModel> {
        return api.createNote(body)
    }

    override suspend fun updateNote(body: UpdateNoteDto, noteId:String): Response<UpdatedNoteModel> {
        return api.updateNote(noteId,body)
    }

    override suspend fun deleteNote(noteId:String): Response<DeletedNoteModel> {
        return api.deleteNote(noteId)
    }
}