package com.example.notes.domain.repository

import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.NoteDto
import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.data.remote.dto.UpdateNoteDto

interface NotesRepository {
    suspend fun getNotes(): List<NoteDto>? = null
    suspend fun createNote(body: CreateNoteDto): ResponseDto? = null
    suspend fun updateNote(body: UpdateNoteDto): ResponseDto? = null
    suspend fun deleteNote(id: String): ResponseDto? = null
}