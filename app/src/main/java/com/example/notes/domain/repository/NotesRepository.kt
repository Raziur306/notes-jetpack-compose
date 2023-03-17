package com.example.notes.domain.repository

import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.NoteDto
import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import retrofit2.Response

interface NotesRepository {
    suspend fun getNotes(): Response<List<NoteDto>>? = null
    suspend fun createNote(body: CreateNoteDto): Response<ResponseDto>? = null
    suspend fun updateNote(body: UpdateNoteDto): Response<ResponseDto>? = null
    suspend fun deleteNote(id: String): Response<ResponseDto>? = null
}