package com.example.notes.presentation.screens.note_viewer

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.common.NetworkResponse
import com.example.notes.data.remote.dto.CreateNoteDto
import com.example.notes.data.remote.dto.UpdateNoteDto
import com.example.notes.domain.use_case.delete_note.DeleteNoteUseCase
import com.example.notes.domain.use_case.new_note.NewNoteUseCase
import com.example.notes.domain.use_case.update_note.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteViewerViewModel @Inject constructor(
    private val newNoteUseCase: NewNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NoteViewerState())
    val state: State<NoteViewerState> = _state


    fun createNote(body: CreateNoteDto) {
        newNoteUseCase(body).onEach { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    _state.value = NoteViewerState(isLoading = true)
                }

                is NetworkResponse.Success -> {
                    _state.value = NoteViewerState(data = result.data)
                }

                is NetworkResponse.Error -> {
                    _state.value = NoteViewerState(errorMessage = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }


    //for updating note
    fun updateNote(body: UpdateNoteDto, noteId: String) {
        updateNoteUseCase(body, noteId).onEach { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    _state.value = NoteViewerState(isLoading = true)
                }

                is NetworkResponse.Success -> {
                    _state.value = NoteViewerState(data = result.data)
                    Log.d("Update Response", result.data.toString())

                }

                is NetworkResponse.Error -> {
                    _state.value = NoteViewerState(errorMessage = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteNote(noteId: String) {
        deleteNoteUseCase(noteId).onEach { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    _state.value = NoteViewerState(isLoading = true)
                }

                is NetworkResponse.Success -> {
                    _state.value = NoteViewerState(data = result.data)
                    Log.d("Update Response", result.data.toString())

                }

                is NetworkResponse.Error -> {
                    _state.value = NoteViewerState(errorMessage = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

}