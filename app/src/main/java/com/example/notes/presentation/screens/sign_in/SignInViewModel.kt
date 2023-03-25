package com.example.notes.presentation.screens.sign_in

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.common.NetworkResponse
import com.example.notes.common.Preferences
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.domain.use_case.sign_in.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val preferences: Preferences
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> get() = _state

    fun signIn(email: String, password: String, isChecked: Boolean) {
        val body = SignInDto(email, password);
        signInUseCase(body).onEach { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    _state.value = SignInState(isLoading = true)
                }

                is NetworkResponse.Success -> {
                    preferences.saveToken(result.data!!.token)
                    if (isChecked) {
                        preferences.setRememberUser(true)
                    }
                    _state.value = SignInState(data = result.data)
                }

                is NetworkResponse.Error -> {

                    _state.value = SignInState(errorMessage = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }


}