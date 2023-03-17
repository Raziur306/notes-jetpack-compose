package com.example.notes.domain.repository

import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.data.remote.dto.RegisterDto
import retrofit2.Response

interface AuthRepository {
    suspend fun signIn(body: SignInDto): Response<ResponseDto>? = null
    suspend fun sinUp(body: RegisterDto): Response<ResponseDto>? = null
}