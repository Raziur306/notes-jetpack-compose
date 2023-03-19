package com.example.notes.presentation.screens.sign_in.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.presentation.screens.sign_in.SignInViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    loading: (Boolean) -> Unit,
    signUpClick: () -> Unit,
    signInSuccess: () -> Unit,
) {

    val state = viewModel.state
    val context = LocalContext.current
    //screen touch flag
    loading(state.value.isLoading)

    //error message
    if (state.value.errorMessage != null && state.value.errorMessage!!.isNotEmpty()) {
        Toast.makeText(context, state.value.errorMessage, Toast.LENGTH_SHORT).show()
    }

    //signIn successful
    if (state.value.data?.response == true) {
        signInSuccess()
        Toast.makeText(context, state.value.data!!.message, Toast.LENGTH_SHORT).show()
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
            viewModel.signIn(email, password)
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

//    progressbar
    if (state.value.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }


}


