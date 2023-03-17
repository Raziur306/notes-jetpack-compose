package com.example.notes.presentation.graphs.notes

sealed class NotesScreen(val route: String) {
    object DashboardScreen : NotesScreen("dashboard_Screen")
    object NewNoteScreen : NotesScreen("new_notes")
    object UpdateNoteScreen : NotesScreen("update_notes")
}