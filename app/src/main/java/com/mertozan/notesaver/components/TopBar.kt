package com.mertozan.notesaver.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.notesaver.R

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