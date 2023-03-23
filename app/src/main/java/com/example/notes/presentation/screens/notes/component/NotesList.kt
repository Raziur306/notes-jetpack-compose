package com.example.notes.presentation.screens.notes.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.notes.data.remote.dto.NoteDto
import kotlin.math.roundToInt

@Composable
fun NotesList(
    data: List<NoteDto>,
    agendaView: Boolean,
    toolbarOffsetHeightPx: MutableState<Float>
) {
    LazyVerticalGrid(
        modifier = Modifier.offset {
            IntOffset(
                x = 0,
                y = toolbarOffsetHeightPx.value.roundToInt()
            )
        },
        columns = GridCells.Adaptive(minSize = if (agendaView) 180.dp else 1000.dp),
        contentPadding = PaddingValues(top = 70.dp, end = 10.dp, start = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(10) {
            NoteCard(title = data[it].name, body = data[it].content, id = "")
        }
    }
}