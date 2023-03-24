package com.example.notes.presentation.splash

import android.os.Looper
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.notes.common.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import android.os.Handler
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {
    private val _state = mutableStateOf<SplashState>(SplashState())
    val state: State<SplashState> = _state

    init {
        val userFound = preferences.rememberStatus()
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            _state.value = SplashState(tokenExist = userFound, navTrigger = true)
        }, 2500)
    }


}