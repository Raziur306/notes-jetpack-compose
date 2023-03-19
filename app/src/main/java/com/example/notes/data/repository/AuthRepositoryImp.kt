package com.example.notes.data.repository

import com.example.notes.data.remote.Api
import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.data.remote.dto.RegisterDto
import com.example.notes.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val api: Api) : AuthRepository {
    override suspend fun signIn(body: SignInDto): Response<ResponseDto> {
        return api.login(body)
    }

    override suspend fun signUp(body: RegisterDto): Response<ResponseDto> {
        return api.register(body)
    }
}