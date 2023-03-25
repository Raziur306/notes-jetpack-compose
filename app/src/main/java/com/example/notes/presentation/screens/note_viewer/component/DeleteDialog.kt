package com.example.notes.presentation.screens.note_viewer.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteDialog(confirm: () -> Unit, dismiss: () -> Unit) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                dismiss()
            }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "After confirmation you can't cancel the process." +
                                "Are you really want to delete this note? ",
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(modifier = Modifier.align(Alignment.End)) {
                        TextButton(
                            onClick = {
                                confirm()
                                openDialog.value = false
                            },
                        ) {
                            Text("Dismiss")
                        }
                        TextButton(
                            onClick = {
                                confirm()
                                openDialog.value = false
                            },
                        ) {
                            Text("Confirm", color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}