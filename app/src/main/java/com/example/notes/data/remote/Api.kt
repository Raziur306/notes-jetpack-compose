package com.example.notes.data.remote

import com.example.notes.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH

interface Api {
    @POST("/api/register")
    suspend fun register(@Body body: RegisterDto): ResponseDto

    @POST("/api/login")
    suspend fun login(@Body body: SignInDto): ResponseDto

    @POST("/api/notes")
    suspend fun createNote(@Body body: CreateNoteDto): ResponseDto

    @GET("/api/notes")
    suspend fun getNotes(): List<NoteDto>

    @PATCH("/api/notes")
    suspend fun updateNote(@Body body: UpdateNoteDto): ResponseDto

    @DELETE("/api/notes")
    suspend fun deleteNote(@Body id: String): ResponseDto
}