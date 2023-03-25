package com.example.notes.data.repository

import com.example.notes.data.remote.NotesApi
import com.example.notes.data.remote.UserApi
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.data.remote.dto.RegisterDto
import com.example.notes.domain.model.AuthModel
import com.example.notes.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val api: UserApi) : AuthRepository {
    override suspend fun signIn(body: SignInDto): Response<AuthModel> {
        return api.login(body)
    }

    override suspend fun signUp(body: RegisterDto): Response<AuthModel> {
        return api.register(body)
    }
}