package com.example.notes.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings

object NavigationItem {
    val items = listOf(
        NavigationItemModel(icon = Icons.Outlined.Lightbulb, label = "Notes"),
        NavigationItemModel(icon = Icons.Outlined.Notifications, label = "Reminders"),
        NavigationItemModel(icon = Icons.Outlined.Add, label = "Create new label"),
        NavigationItemModel(icon = Icons.Outlined.Archive, label = "Archive"),
        NavigationItemModel(icon = Icons.Outlined.Delete, label = "Trash"),
        NavigationItemModel(icon = Icons.Outlined.Settings, label = "Settings"),
        NavigationItemModel(icon = Icons.Outlined.Help, label = "Hep & feedback"),
    )
}
