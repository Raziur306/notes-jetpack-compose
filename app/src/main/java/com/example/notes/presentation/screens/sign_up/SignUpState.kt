package com.example.notes.presentation.screens.sign_up

import com.example.notes.domain.model.AuthModel

data class SignUpState(
    val isLoading: Boolean = false,
    val data: AuthModel? = null,
    val errorMessage: String? = null
)