package com.example.notes.presentation.screens.note_viewer.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.domain.model.Note
import com.example.notes.domain.model.NoteModel
import com.example.notes.presentation.common.component.Toast
import com.example.notes.presentation.screens.note_viewer.NoteViewerViewModel

@Composable
fun NoteViewerScreen(
    note: Note?,
    viewModel: NoteViewerViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var title: String = ""
    var description: String = ""
    var canDelete: Boolean = false
    var noteId: String = ""
    if (note != null) {
        title = note.title
        description = note.description
        canDelete = true
        noteId = note._id
    }


//response state
    val state = viewModel.state
    val data: NoteModel? = state.value.data
    if (data != null && data.response) {
        Toast(message = "Successful")
    }


    if (state.value.errorMessage != null) {
        Toast(message = state.value.errorMessage.toString())
    }



    Column(modifier = Modifier.fillMaxSize()) {
        NoteComponent(
            _title = title,
            _description = description,
            canDelete = canDelete,
            saveNote = { title, description ->
                if (noteId.isNotBlank()) {
                    viewModel.updateNote(UpdateNoteDto(title, description), noteId = noteId)
                } else {
                    viewModel.createNote(CreateNoteDto(title, description))
                }
            },
            onBackPress = {
                onBackPress()
            }, onDeleteClick = {
                showDialog = !showDialog
            })
    }

    if (showDialog) {
        DeleteDialog(confirm = {
            viewModel.deleteNote(noteId = noteId)
            showDialog = !showDialog
            onBackPress()
        }, dismiss = {
            showDialog = !showDialog
        })
    }


}