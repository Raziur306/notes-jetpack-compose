package com.example.notes.presentation.screens.sign_in

import com.example.notes.data.remote.dto.ResponseDto

data class SignInState(
    val isLoading: Boolean = false,
    val data: ResponseDto? = null,
    val errorMessage: String? = null
)