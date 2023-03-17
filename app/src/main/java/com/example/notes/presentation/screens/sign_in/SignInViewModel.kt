package com.example.notes.presentation.screens.sign_in

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.common.NetworkResponse
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.domain.use_case.sign_in.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {
    var email: String = ""
    var password: String = ""
    var checkBox: Boolean = false
    var signInErrorState = false

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    fun signIn() {
        val body = SignInDto(email, password);
        signInUseCase(body).onEach { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    _state.value = SignInState(isLoading = true)
                }
                is NetworkResponse.Success -> {
                    _state.value = SignInState(data = result.data)
                }
                is NetworkResponse.Error -> {

                    _state.value = SignInState(errorMessage = result.message)

                }
            }
        }.launchIn(viewModelScope)
    }
}