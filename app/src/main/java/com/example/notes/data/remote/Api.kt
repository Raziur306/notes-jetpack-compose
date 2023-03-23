package com.example.notes.data.remote

import com.example.notes.data.remote.dto.*
import com.example.notes.data.repository.model.AuthModel
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

interface Api {
    @POST("/api/register")
    suspend fun register(@Body body: RegisterDto): Response<AuthModel>

    @POST("/api/login")
    suspend fun login(@Body body: SignInDto): Response<AuthModel>

    @POST("/api/notes")
    suspend fun createNote(@Body body: CreateNoteDto): Response<CreatedNoteModel>

    @GET("/api/notes")
    suspend fun getNotes(): Response<NotesModel>

    @PUT("/api/notes")
    suspend fun updateNote(@Body body: UpdateNoteDto): Response<UpdatedNoteModel>

    @DELETE("/api/notes")
    suspend fun deleteNote(@Body id: String): Response<DeletedNoteModel>
}