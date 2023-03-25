package com.example.notes.presentation.screens.notes.component


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.domain.model.Note
import com.example.notes.presentation.common.component.Toast
import com.example.notes.presentation.graphs.notes.NotesRoutes
import com.example.notes.presentation.screens.notes.NotesState
import com.example.notes.presentation.screens.notes.NotesViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    agendaView: Boolean,
    toolbarOffsetHeightPx: MutableState<Float>,
    onItemClick: (Note) -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getNotes()
    }

    val state: State<NotesState> = viewModel.state
    val notes: List<Note>? = state.value.data

    if (!state.value.errorMessage.isNullOrEmpty()) {
        Toast(message = state.value.errorMessage ?: "Something went wrong")
    }

    if (state.value.isLoading) {
        DefaultContent()
    } else if (!notes.isNullOrEmpty()) {
        NotesList(
            notes = notes,
            agendaView,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx
        ) { clickedNote ->
            onItemClick(clickedNote)
        }
    } else {
        DefaultContent()
    }
}