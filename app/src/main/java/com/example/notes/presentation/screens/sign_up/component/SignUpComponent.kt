package com.example.notes.presentation.screens.sign_up.component

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
import androidx.compose.material.icons.filled.Person
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpComponent(
    onSignUpClick: (name: String, email: String, password: String) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }

    Column {
        TextField(
            value = name,
            onValueChange = { newName ->
                name = newName
            },
            label = { Text("Name") },
            placeholder = { Text(text = "Enter your name") },
            trailingIcon = { Icons.Default.Person },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

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


        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                onSignUpClick(name, email, password)
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()

        ) {
            Text("Sign up")
        }

    }
}