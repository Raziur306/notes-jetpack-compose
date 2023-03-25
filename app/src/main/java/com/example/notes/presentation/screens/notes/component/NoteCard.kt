package com.example.notes.presentation.screens.notes.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.domain.model.Note


@Composable
fun NoteCard(
    note: Note,
    onItemClick: (Note) -> Unit
) {
    OutlinedCard(
        border = CardDefaults.outlinedCardBorder().copy(0.dp),
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onItemClick(note)
            },

        ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = note.title?:"",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = note.description?:"",
                maxLines = 9,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}