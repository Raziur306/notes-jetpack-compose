package com.example.notes.data.remote

import com.example.notes.data.remote.dto.*
import com.example.notes.data.repository.model.CreatedNoteModel
import com.example.notes.data.repository.model.DeletedNoteModel
import com.example.notes.data.repository.model.NotesModel
import com.example.notes.data.repository.model.UpdatedNoteModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotesApi {
    @POST("/api/notes")
    suspend fun createNote(@Body body: CreateNoteDto): Response<CreatedNoteModel>

    @GET("/api/notes")
    suspend fun getNotes(): Response<NotesModel>

    @PUT("/api/notes/{noteId}")
    suspend fun updateNote(@Path("noteId")noteId:String,@Body body: UpdateNoteDto): Response<UpdatedNoteModel>

    @DELETE("/api/notes/{noteId}")
    suspend fun deleteNote(@Path("noteId")noteId:String): Response<DeletedNoteModel>
}