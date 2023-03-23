package com.example.notes.presentation.screens.sign_in.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignInComponent(
    onSignInClick: (email: String, password: String, checkBox: Boolean) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var checkBox by rememberSaveable { mutableStateOf(false) }

    Column {
        TextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
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
                    onCheckedChange = { isChecked ->
                        checkBox = isChecked
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
                onSignInClick(email, password, checkBox)
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()

        ) {
            Text("Sign in")
        }

    }
}