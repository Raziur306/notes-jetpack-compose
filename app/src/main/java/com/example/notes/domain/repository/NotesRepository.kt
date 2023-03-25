package com.example.notes.domain.repository

import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.domain.model.Note
import com.example.notes.domain.model.NoteModel
import retrofit2.Response

interface NotesRepository {
    suspend fun getNotes(): Response<List<Note>>? = null
    suspend fun createNote(body: CreateNoteDto): Response<NoteModel>? = null
    suspend fun updateNote(body: UpdateNoteDto, noteId: String): Response<NoteModel>? = null
    suspend fun deleteNote(noteId: String): Response<NoteModel>? = null
}