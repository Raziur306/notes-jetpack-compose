package com.example.notes.presentation.screens.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.common.NetworkResponse
import com.example.notes.common.Preferences
import com.example.notes.data.remote.dto.RegisterDto
import com.example.notes.domain.use_case.sign_up.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val preferences: Preferences
) : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    fun signUp(name: String, email: String, password: String) {
        val body = RegisterDto(name, email, password);
        signUpUseCase(body).onEach { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    _state.value = SignUpState(isLoading = true)
                }

                is NetworkResponse.Success -> {
                    preferences.saveToken(result.data!!.token)
                    _state.value = SignUpState(data = result.data)
                }

                is NetworkResponse.Error -> {

                    _state.value = SignUpState(errorMessage = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}