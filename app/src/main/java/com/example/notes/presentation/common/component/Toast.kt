package com.example.notes.presentation.common.component

import android.widget.Toast.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(message: String) {
    makeText(LocalContext.current, message, LENGTH_SHORT).show()
}