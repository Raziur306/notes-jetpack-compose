package com.example.notes.presentation.graphs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notes.presentation.graphs.auth.authNavGraph
import com.example.notes.presentation.graphs.notes.NotesRoutes
import com.example.notes.presentation.graphs.notes.notesNavGraph
import com.example.notes.presentation.splash.component.SplashScreen


@Composable
fun RootNavigationGraph(navHostController: NavHostController, context: Context) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        authNavGraph(navHostController, context)
        notesNavGraph(navHostController = navHostController)

        composable(Graph.SPLASH) {
            SplashScreen(navigate = { isUserExist ->
                if (isUserExist) {
                    navHostController.popBackStack()
                    navHostController.navigate(NotesRoutes.NotesScreen.route)
                } else {
                    navHostController.navigate(Graph.AUTHENTICATION)
                }
            })
        }
    }
}