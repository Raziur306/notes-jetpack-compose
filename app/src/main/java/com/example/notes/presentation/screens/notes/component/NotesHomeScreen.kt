package com.example.notes.presentation.screens.notes.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notes.domain.model.Note
import com.example.notes.presentation.common.NavigationItem
import kotlinx.coroutines.launch

@Composable
fun NotesHomeScreen(
    onNoteClick: (Note) -> Unit,
    onNewNoteClick: () -> Unit
) {
    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedItem = remember { mutableStateOf(NavigationItem.items[0]) }
    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Text(
                    "Personal Notes",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(20.dp)
                )
                NavigationItem.items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.label) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(end = 12.dp),
                        shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            selectedIconColor = MaterialTheme.colorScheme.surfaceTint,
                            selectedBadgeColor = MaterialTheme.colorScheme.surfaceTint,
                            selectedTextColor = MaterialTheme.colorScheme.surfaceTint
                        )
                    )
                }
            }
        },

        content = {
            NotesScaffold(drawerState = drawerState, onNewNoteClick = {
                onNewNoteClick()
            }, onNoteClick = { note ->
                onNoteClick(note)
            })
        })
}

