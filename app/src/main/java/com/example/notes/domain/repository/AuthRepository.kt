package com.example.notes.domain.repository

import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.data.remote.dto.RegisterDto
import com.example.notes.domain.model.AuthModel
import retrofit2.Response

interface AuthRepository {
    suspend fun signIn(body: SignInDto): Response<AuthModel>? = null
    suspend fun signUp(body: RegisterDto): Response<AuthModel>? = null
}