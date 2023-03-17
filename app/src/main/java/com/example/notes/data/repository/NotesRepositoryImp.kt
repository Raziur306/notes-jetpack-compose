package com.example.notes.data.repository

import com.example.notes.data.remote.Api
import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.NoteDto
import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.domain.repository.NotesRepository
import retrofit2.Response

import javax.inject.Inject

class NotesRepositoryImp @Inject constructor(private val api: Api) : NotesRepository {
    override suspend fun getNotes(): Response<List<NoteDto>> {
        return api.getNotes()
    }

    override suspend fun createNote(body: CreateNoteDto): Response<ResponseDto> {
        return api.createNote(body)
    }

    override suspend fun updateNote(body: UpdateNoteDto): Response<ResponseDto> {
        return api.updateNote(body)
    }

    override suspend fun deleteNote(id: String): Response<ResponseDto> {
        return api.deleteNote(id)
    }
}