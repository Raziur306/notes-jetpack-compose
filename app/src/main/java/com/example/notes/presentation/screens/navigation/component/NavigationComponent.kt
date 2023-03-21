package com.example.notes.presentation.screens.navigation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notes.presentation.screens.navigation.NavigationItemModel
import com.example.notes.presentation.screens.notes.component.NotesScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationComponent() {
    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(
        NavigationItemModel(icon = Icons.Outlined.Lightbulb, label = "Notes"),
        NavigationItemModel(icon = Icons.Outlined.Notifications, label = "Reminders"),
        NavigationItemModel(icon = Icons.Outlined.Add, label = "Create new label"),
        NavigationItemModel(icon = Icons.Outlined.Archive, label = "Archive"),
        NavigationItemModel(icon = Icons.Outlined.Delete, label = "Trash"),
        NavigationItemModel(icon = Icons.Outlined.Settings, label = "Settings"),
        NavigationItemModel(icon = Icons.Outlined.Help, label = "Hep & feedback"),
    )
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Text(
                    "Personal Notes",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(20.dp)
                )

                items.forEach { item ->
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
        }, content = {

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

                }
            }) { paddingValue ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues = paddingValue)
                        .fillMaxSize()
                        .nestedScroll(nestedScrollConnection)
                ) {
                    NotesScreen(agendaView = agendaView)
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
        })
}

