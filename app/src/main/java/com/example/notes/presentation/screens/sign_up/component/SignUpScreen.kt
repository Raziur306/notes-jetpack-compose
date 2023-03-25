package com.example.notes.presentation.screens.sign_up.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.domain.model.AuthModel
import com.example.notes.presentation.common.component.Toast
import com.example.notes.presentation.screens.sign_in.component.LoadingProgress
import com.example.notes.presentation.screens.sign_up.SignUpState
import com.example.notes.presentation.screens.sign_up.SignUpViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    loading: (Boolean) -> Unit,
    signUpSuccess: () -> Unit,
) {

    val state: State<SignUpState> = viewModel.state
    val data: AuthModel? = state.value.data


    //error message
    if (!state.value.errorMessage.isNullOrEmpty()) {
        Toast(message = state.value.errorMessage ?: "Something went wrong")
    }

    //signUp successful
    if (data != null && data.response) {
        LaunchedEffect(Unit) {
            signUpSuccess()
        }
        Toast(message = state.value.data!!.message)
    }


    //component
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(text = "Hey! Welcome", fontSize = 26.sp, fontWeight = FontWeight.Bold)

        Text(
            text = "Sign up with your email or password\nor login with your existing account.",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(130.dp))

        //sign in component
        SignUpComponent(onSignUpClick = { name, email, password ->
            viewModel.signUp(name, email, password)
        })

    }

//    progressbar
    if (state.value.isLoading) {
        LoadingProgress()
        loading(true)
    } else {
        loading(false)
    }

}
