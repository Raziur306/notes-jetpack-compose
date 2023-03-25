package com.example.notes.presentation.graphs.auth

sealed class AuthRoutes(val route: String) {
    object SignInScreen : AuthRoutes("sign_in")
    object SignUpScreen : AuthRoutes("sign_up")
}