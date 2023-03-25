package com.example.notes.data.repository

import com.example.notes.data.remote.NotesApi
import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.domain.model.Note
import com.example.notes.domain.model.NoteModel
import com.example.notes.domain.repository.NotesRepository
import retrofit2.Response

import javax.inject.Inject

class NotesRepositoryImp @Inject constructor(private val api: NotesApi) : NotesRepository {
    override suspend fun getNotes(): Response<List<Note>> {
        return api.getNotes()
    }

    override suspend fun createNote(body: CreateNoteDto): Response<NoteModel> {
        return api.createNote(body)
    }

    override suspend fun updateNote(body: UpdateNoteDto, noteId: String): Response<NoteModel> {
        return api.updateNote(noteId, body)
    }

    override suspend fun deleteNote(noteId: String): Response<NoteModel> {
        return api.deleteNote(noteId)
    }
}