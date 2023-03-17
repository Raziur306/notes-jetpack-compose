package com.example.notes.presentation.graphs.auth

sealed class AuthScreen(val route: String) {
    object SignInScreen : AuthScreen("sign_in")
    object SignUpScreen : AuthScreen("sign_up")
}