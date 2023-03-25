package com.example.notes.presentation.screens.notes

import com.example.notes.domain.model.Note

data class NotesState(
    val isLoading: Boolean = false,
    val data: List<Note>? = null,
    val errorMessage: String? = null
)