package com.example.notes.presentation.screens.sign_in.component

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.domain.model.AuthModel
import com.example.notes.presentation.common.component.Toast
import com.example.notes.presentation.screens.sign_in.SignInState
import com.example.notes.presentation.screens.sign_in.SignInViewModel


@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    loading: (Boolean) -> Unit,
    signUpClick: () -> Unit,
    signInSuccess: () -> Unit
) {
    val context: Context = LocalContext.current
    val state: SignInState by viewModel.state.collectAsState()
    val data: AuthModel? = state.data


//error message
    if (!state.errorMessage.isNullOrEmpty()) {
        Toast(message = state.errorMessage.toString())
    }

    if (data != null && data.response) {
        LaunchedEffect(Unit) {
            signInSuccess()
        }
        Toast(message = data.message ?: "Something went wrong")
    }

    //component
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(text = "Welcome Back", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Sign in with your email or password\nor create a new account.",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(130.dp))

//sign in component
        SignInComponent(onSignInClick = { email, password, isChecked ->
            viewModel.signIn(email, password, isChecked)
        })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account? ")
            Text(
                text = "Sign Up",
                color = Color.Cyan,
                modifier = Modifier.clickable {
                    signUpClick()
                })
        }
    }

//progressbar
    if (state.isLoading) {
        LoadingProgress()
        loading(true)
    } else {
        loading(false)
    }
}