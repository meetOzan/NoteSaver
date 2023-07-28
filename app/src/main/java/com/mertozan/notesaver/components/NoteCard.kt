package com.mertozan.notesaver.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertozan.notesaver.R
import com.mertozan.notesaver.data.Note
import com.mertozan.notesaver.viewModel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier, note: Note, onDelete: () -> Unit
) {
    val viewModel = hiltViewModel<NoteViewModel>()

    var isExtended by remember {
        mutableStateOf(false)
    }

    // For Alert Dialog
    val openDialog = remember { mutableStateOf(false) }
    var title by remember { mutableStateOf(note.title) }
    var body by remember { mutableStateOf(note.body) }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 16.dp)
        .then(modifier),
        shape = ShapeDefaults.Large,
        color = Color(note.background),
        border = BorderStroke(
            0.5.dp, Brush.linearGradient(
                listOf(Color.Black, Color.DarkGray, Color.LightGray, Color.Black)
            )
        ),
        onClick = { isExtended = !isExtended }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                color = if (note.isImportant && note.background != 0xFFFF6F00) {
                    Color.Red
                } else if (!note.isImportant) {
                    Color.Black
                } else Color.White,
                fontSize = 24.sp,
            )
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = note.id.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(2f),
                    color = if (note.isImportant && note.background != 0xFFFF6F00) {
                        Color.Red
                    } else if (!note.isImportant) {
                        Color.Black
                    } else Color.White,
                    fontSize = 48.sp,
                )
                AnimatedVisibility(visible = isExtended) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(onClick = onDelete, Modifier.size(28.dp)) {
                            Icon(
                                imageVector = Icons.Filled.Delete, contentDescription = "delete"
                            )
                        }
                        IconButton(onClick = { openDialog.value = true }, Modifier.size(28.dp)) {
                            Icon(
                                imageVector = Icons.Filled.Settings, contentDescription = "delete"
                            )
                        }
                    }
                }
            }
            if (openDialog.value) {
                AnimatedVisibility(visible = openDialog.value) {
                    // I have used AlertDialog here but best practice is to use Dialog and Card in it.
                    AlertDialog(onDismissRequest = { openDialog.value = false },
                        title = { Text("Update note.", Modifier.padding(8.dp)) },
                        // It's for use all screen width
                        properties = DialogProperties(
                            usePlatformDefaultWidth = false
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.new_title),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Normal, fontSize = 18.sp
                                        ),
                                        modifier = Modifier.padding(end = 16.dp)
                                    )
                                    TextField(
                                        value = title, onValueChange = {
                                            title = it
                                        },
                                        Modifier
                                            .border(
                                                width = 0.4.dp,
                                                shape = ShapeDefaults.Large,
                                                brush = Brush.linearGradient(
                                                    listOf(
                                                        Color.Black,
                                                        Color.DarkGray,
                                                        Color.LightGray,
                                                        Color.Red
                                                    )
                                                )
                                            )
                                            .clip(ShapeDefaults.Large)
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Text(
                                        text = stringResource(R.string.new_message),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Normal, fontSize = 18.sp
                                        ),
                                        modifier = Modifier.padding(end = 16.dp)
                                    )
                                    TextField(
                                        value = body,
                                        onValueChange = {
                                            body = it
                                        },
                                        Modifier
                                            .border(
                                                width = 0.4.dp,
                                                shape = ShapeDefaults.Large,
                                                brush = Brush.linearGradient(
                                                    listOf(
                                                        Color.Black,
                                                        Color.DarkGray,
                                                        Color.LightGray,
                                                        Color.Red
                                                    )
                                                )
                                            )
                                            .clip(ShapeDefaults.Large),
                                    )
                                }
                            }
                        },
                        confirmButton = {
                            Button(onClick = {
                                viewModel.updateNote(
                                    Note(
                                        id = note.id,
                                        title = title,
                                        body = body,
                                        isImportant = note.isImportant,
                                        fontStyle = note.fontStyle,
                                        fontSize = note.fontSize,
                                        fontWeight = note.fontWeight,
                                        background = note.background
                                    )
                                )
                                openDialog.value = false
                            }) {
                                Text(stringResource(R.string.update_note))
                            }

                        })
                }
            }
            val extraPadding by animateDpAsState(
                8.dp, animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow,
                    visibilityThreshold = null,
                ), label = ""
            )
            AnimatedVisibility(visible = isExtended) {
                Text(
                    text = note.body,
                    modifier = Modifier.padding(horizontal = extraPadding, vertical = 6.dp),
                    textAlign = TextAlign.Center,
                    fontSize = if (!note.fontSize) 16.sp else 24.sp,
                    fontWeight = if (!note.fontWeight) FontWeight.Normal else FontWeight.Bold,
                    fontStyle = if (note.fontStyle) FontStyle.Italic else FontStyle.Italic,
                    maxLines = if (!isExtended) 1 else 100,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewNote() {
    NoteCard(note = Note(
        1, "GelGelGel", "GelGelGel GelGelGel GelGelGel GelGelGel ".repeat(4), false
    ), onDelete = {})
}