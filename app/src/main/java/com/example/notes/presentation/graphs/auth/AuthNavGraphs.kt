package com.example.notes.presentation.graphs.auth

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.notes.presentation.graphs.Graph
import com.example.notes.presentation.graphs.notes.NotesScreen
import com.example.notes.presentation.screens.sign_in.SignInViewModel
import com.example.notes.presentation.screens.sign_in.component.SignInScreen
import com.example.notes.presentation.screens.sign_up.component.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController, context: Context) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthScreen.SignInScreen.route) {
        composable(route = AuthScreen.SignInScreen.route) {
            SignInScreen(loading = { isLoading ->
                if (isLoading) {
                    (context as Activity).window.setFlags(

                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                } else {
                    (context as Activity).window.clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                }
            },
                signUpClick = {
                    navController.navigate(AuthScreen.SignUpScreen.route)
                },
                signInSuccess = {
                    navController.popBackStack()
                    navController.navigate(NotesScreen.DashboardScreen.route)
                })
        }
        composable(route = AuthScreen.SignUpScreen.route) {
            SignUpScreen(loading = { isLoading ->
                if (isLoading) {
                    (context as Activity).window.setFlags(

                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                } else {
                    (context as Activity).window.clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                }
            },
                signInClick = {
                    navController.navigate(AuthScreen.SignInScreen.route)
                },
                signUpSuccess = {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.SignInScreen.route)
                })
        }
    }


}