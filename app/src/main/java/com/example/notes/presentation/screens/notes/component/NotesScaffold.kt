package com.example.notes.presentation.screens.notes.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.notes.domain.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NotesScaffold(
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState,
    onNoteClick: (Note) -> Unit,
    onNewNoteClick: () -> Unit
) {
    val toolbarHeightPx = with(LocalDensity.current) { (58.dp).roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    //nested scroll connection
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    var agendaView by rememberSaveable { mutableStateOf(true) }

    Scaffold(bottomBar = {
        BottomBar() {
            onNewNoteClick()
        }
    }) { paddingValue ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValue)
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            NotesScreen(
                agendaView = agendaView,
                toolbarOffsetHeightPx = toolbarOffsetHeightPx
            ) { note ->
                onNoteClick(note)
            }
            TopBar(toolbarOffsetHeightPx,
                launchDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                }, onViewGrid = { isAgenda ->
                    agendaView = isAgenda
                })
        }
    }

}