package com.mertozan.notesaver.ui.theme.screens

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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertozan.composesaver.viewModel.NoteViewModel
import com.mertozan.notesaver.R
import com.mertozan.notesaver.components.FloatingActionButton
import com.mertozan.notesaver.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllNoteScreen(
    onFabClicked: () -> Unit,
) {
    Scaffold(topBar = { TopBar() }, floatingActionButton = {
        FloatingActionButton(
            onFabClicked
        )
    }
    ) { padding ->
        NoteScreen(Modifier.padding(padding))
    }
}

@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = hiltViewModel()
) {

    val allNotes = viewModel.allNotes.observeAsState()
    val allNotesS: List<Note> = allNotes.value ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 48.dp),
        verticalArrangement = if (allNotesS.isEmpty()) Arrangement.Center else Arrangement.Top
    ) {
        if (allNotesS.isEmpty()) {
            EmptyPlaceHolder()
        } else {
            LazyColumn(modifier = modifier
                .fillMaxSize()
                .padding(bottom = 8.dp, top = 4.dp),
                content = {
                    items(allNotesS) {
                        NoteCard(
                            note = it,
                            onDelete = { viewModel.deleteNote(it) },
                            onUpdate = {},
                        )
                    }
                })
        }
    }
}

@Composable
fun TopBar() {
    Surface(
        color = MaterialTheme.colorScheme.onSecondaryContainer, modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.welcome_to_the_note_saver),
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
            color = Color.White,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 24.sp
        )
    }
}

@Composable
fun EmptyPlaceHolder() {
    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = "no note",
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

@Preview
@Composable
fun PrevNotes() {
}