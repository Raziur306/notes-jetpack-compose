package com.example.notes.data.repository

import com.example.notes.data.remote.Api
import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.data.remote.dto.SignUpDto
import com.example.notes.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val api: Api) : AuthRepository {
    override suspend fun signIn(body: SignInDto): ResponseDto {
        return api.login(body)
    }

    override suspend fun sinUp(body: SignUpDto): ResponseDto {
        return api.register(body)
    }
}