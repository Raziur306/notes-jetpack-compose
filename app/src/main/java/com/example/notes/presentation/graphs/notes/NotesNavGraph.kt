package com.example.notes.presentation.graphs.notes

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.notes.common.Constants
import com.example.notes.domain.model.Note
import com.example.notes.presentation.graphs.Graph
import com.example.notes.presentation.screens.note_viewer.component.NoteViewerScreen
import com.example.notes.presentation.screens.notes.component.NotesHomeScreen
import com.example.notes.presentation.screens.notes.component.NotesScaffold



fun NavGraphBuilder.notesNavGraph(navHostController: NavHostController) {
    var noteData: Note? = null
    navigation(
        startDestination = NotesRoutes.NotesScreen.route,
        route = Graph.NOTE
    ) {

        composable(NotesRoutes.NotesScreen.route) {
            NotesHomeScreen(onNoteClick = { note ->
                noteData = note
                navHostController.navigate(NotesRoutes.NoteViewerScreen.route)
            }, onNewNoteClick = {
                noteData = null
                navHostController.navigate(NotesRoutes.NoteViewerScreen.route)
            })
        }
        composable(NotesRoutes.NoteViewerScreen.route) {
            NoteViewerScreen(note = noteData, onBackPress = {
                navHostController.popBackStack()
            })
        }

    }
}