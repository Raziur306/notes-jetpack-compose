package com.example.notes.presentation.screens.note_viewer

import com.example.notes.data.remote.dto.ResponseDto

data class NoteViewerState(
    val isLoading: Boolean = false,
    val data: ResponseDto? = null,
    val errorMessage: String? = null


)
