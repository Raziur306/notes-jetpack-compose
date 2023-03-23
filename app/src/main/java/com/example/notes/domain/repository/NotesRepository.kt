package com.example.notes.domain.repository

import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.data.repository.model.CreatedNoteModel
import com.example.notes.data.repository.model.DeletedNoteModel
import com.example.notes.data.repository.model.NotesModel
import com.example.notes.data.repository.model.UpdatedNoteModel
import retrofit2.Response

interface NotesRepository {
    suspend fun getNotes(): Response<NotesModel>? = null
    suspend fun createNote(body: CreateNoteDto): Response<CreatedNoteModel>? = null
    suspend fun updateNote(body: UpdateNoteDto, noteId:String): Response<UpdatedNoteModel>? = null
    suspend fun deleteNote(noteId: String): Response<DeletedNoteModel>? = null
}