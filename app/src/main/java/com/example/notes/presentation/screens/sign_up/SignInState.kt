package com.example.notes.presentation.screens.sign_up

import com.example.notes.data.remote.dto.ResponseDto

data class SignUpState(
    val isLoading: Boolean = false,
    val data: ResponseDto? = null,
    val errorMessage: String? = null
)