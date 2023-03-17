package com.example.notes.presentation.graphs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.notes.presentation.graphs.auth.authNavGraph
import com.example.notes.presentation.graphs.notes.notesNavGraph



@Composable
fun RootNavigationGraph(navHostController: NavHostController, context: Context) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navHostController, context)
        notesNavGraph(navHostController)
    }
}