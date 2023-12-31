package com.mertozan.notesaver.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.notesaver.components.FloatingActionButton
import com.mertozan.notesaver.components.NoteCard
import com.mertozan.notesaver.components.TopBar
import com.mertozan.notesaver.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllNoteScreen(
    onFabClicked: () -> Unit,
    allNoteZ: List<Note>,
    delete: (Note) -> Unit
) {
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = { FloatingActionButton(onFabClicked) }
    ) { padding ->
        NoteScreen(
            modifier = Modifier.padding(padding),
            allNoteZ = allNoteZ,
            delete = delete
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    allNoteZ: List<Note>,
    delete: (Note) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        verticalArrangement =
        if (allNoteZ.isEmpty()) Arrangement.Center
        else Arrangement.Top
    ) {
        if (allNoteZ.isEmpty()) {
            EmptyPlaceHolder()
        } else {
            LazyColumn(modifier = modifier
                .fillMaxSize()
                .padding(bottom = 8.dp, top = 4.dp),
                content = {
                    items(allNoteZ, key = { it.id }) {
                        NoteCard(
                            note = it,
                            onDelete = {
                                delete.invoke(it)
                            },
                            modifier = Modifier.animateItemPlacement()
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun EmptyPlaceHolder() {
    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.NoteAdd,
            contentDescription = "add note",
            modifier = Modifier
                .padding(16.dp)
                .size(120.dp)
                .alpha(0.5f),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Text(
            text = "No note here, let's add some.",
            modifier = Modifier.padding(12.dp),
            color = Color.DarkGray,
            fontSize = 18.sp,
            style = MaterialTheme.typography.labelSmall
        )
    }
}