package com.example.notes.presentation.screens.notes.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.notes.R
import kotlin.math.roundToInt


@Composable
fun TopBar(
    toolbarOffsetHeightPx: MutableState<Float>,
    onViewGrid: (Boolean) -> Unit,
    launchDrawer: () -> Unit
) {
    var viewAgenda by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) }
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)
            .height(48.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.8.dp))
            .clickable {

            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(
            onClick = { launchDrawer() }) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = "",
            )
        }
        Text("Search your notes", modifier = Modifier.weight(0.8f))

        IconButton(onClick = {
            viewAgenda = !viewAgenda
            onViewGrid(viewAgenda)
        }) {
            Icon(
                imageVector = if (viewAgenda) Icons.Outlined.ViewAgenda else Icons.Default.GridView,
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = "",
            )
        }
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(5.dp))
    }

}