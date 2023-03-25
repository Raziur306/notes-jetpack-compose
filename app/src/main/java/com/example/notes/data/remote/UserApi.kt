package com.example.notes.data.remote

import com.example.notes.data.remote.dto.RegisterDto
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.domain.model.AuthModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/register")
    suspend fun register(@Body body: RegisterDto): Response<AuthModel>

    @POST("/api/login")
    suspend fun login(@Body body: SignInDto): Response<AuthModel>
}