package com.example.notes.presentation.screens.sign_in

import com.example.notes.domain.model.AuthModel

data class SignInState(
    val isLoading: Boolean = false,
    val data: AuthModel? = null,
    val errorMessage: String? = null
)