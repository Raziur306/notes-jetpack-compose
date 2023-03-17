package com.example.notes.presentation.screens.sign_in.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.presentation.common.component.AuthError
import com.example.notes.presentation.screens.sign_in.SignInState
import com.example.notes.presentation.screens.sign_in.SignInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    state: State<SignInState> = viewModel.state,
    loading: (Boolean) -> Unit,
    signUpClick: () -> Unit,
    signInSuccess: () -> Unit
) {
    var email by remember { mutableStateOf(viewModel.email) }
    var password by remember { mutableStateOf(viewModel.password) }
    var checkBox by remember { mutableStateOf(viewModel.checkBox) }
    val context = LocalContext.current


    //screen touch flag
    loading(state.value.isLoading)

    //error message
    if (!state.value.errorMessage.isNullOrEmpty()) {
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

        TextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
                viewModel.email = newEmail
            },
            label = { Text("Email") },
            placeholder = { Text(text = "Enter your email") },
            trailingIcon = { Icons.Default.Email },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))


        TextField(
            onValueChange = { newPass ->
                password = newPass
                viewModel.password = newPass
            },
            value = password,
            placeholder = { Text(text = "Enter your password") },
            label = { Text("Password") },
            trailingIcon = { Icons.Default.Lock },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkBox,
                    onCheckedChange = {
                        checkBox = it
                    },
                )
                Text(text = "Remember me", fontSize = 14.sp)
            }
            Text(
                text = "Forget Password",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {

                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.signIn()
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()

        ) {
            Text("Sign in")
        }
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


