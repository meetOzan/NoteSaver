package com.mertozan.notesaver.ui.theme.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.notesaver.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    color: Color = Color.Cyan,
    onDelete: () -> Unit,
    onUpdate: () -> Unit
) {

    var isExtended by remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 16.dp)
        .then(modifier),
        shape = ShapeDefaults.Large,
        color = color,
        border = BorderStroke(
            0.5.dp,
            Brush.linearGradient(
                listOf(Color.Black, Color.DarkGray, Color.LightGray)
            )
        ), onClick = { isExtended = !isExtended }) {
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
                color = if (note.isImportant) MaterialTheme.colorScheme.error else Color.Black,
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
                    color = if (note.isImportant) MaterialTheme.colorScheme.error else Color.Black,
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
                        IconButton(onClick = onUpdate, Modifier.size(28.dp)) {
                            Icon(
                                imageVector = Icons.Filled.Settings, contentDescription = "delete"
                            )
                        }
                    }
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
                    fontSize = 24.sp,
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
    ), onDelete = {}, onUpdate = {})
}