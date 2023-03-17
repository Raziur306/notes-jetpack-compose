package com.example.notes.domain.repository

import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.data.remote.dto.SignUpDto

interface AuthRepository {
    suspend fun signIn(body:SignInDto):ResponseDto ? = null
    suspend fun sinUp(body:SignUpDto):ResponseDto? = null
}