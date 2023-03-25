package com.example.notes.presentation.screens.note_viewer

import com.example.notes.domain.model.Note
import com.example.notes.domain.model.NoteModel

data class NoteViewerState(
    val isLoading: Boolean = false,
    val data: NoteModel? = null,
    val errorMessage: String? = null
)