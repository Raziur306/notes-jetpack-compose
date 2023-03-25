package com.example.notes.presentation.screens.note_viewer.component

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteComponent(
    canDelete: Boolean,
    _title: String, _description: String,
    saveNote: (title: String, body: String) -> Unit,
    onBackPress: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var title by remember { mutableStateOf(_title) }
    var description by remember { mutableStateOf(_description) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onBackPress() }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colorScheme.outline,
                    contentDescription = "Back Screen",
                )
            }
            Row {

                IconButton(onClick = { onDeleteClick() }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Delete,
                        tint = Color.Red,
                        contentDescription = "Delete Note",
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
                IconButton(onClick = { saveNote(title, description) }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Save,
                        tint = MaterialTheme.colorScheme.outline,
                        contentDescription = "Save Note",
                    )
                }

            }
        }
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.titleLarge,
            placeholder = {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.LightGray)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            value = title,
            onValueChange = { newTitle -> title = newTitle })
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "Note",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            value = description,
            onValueChange = { newDesc -> description = newDesc })
    }
}