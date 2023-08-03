package com.mertozan.notesaver.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.notesaver.data.Note
import com.mertozan.notesaver.viewModel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNote(
    onNavigateNotes: () -> Unit,
    viewModel: NoteViewModel
) {

    var text by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var isImportant by remember { mutableStateOf(false) }
    var cardBackground: Long by remember {
        mutableStateOf(0xFFCCCFCF)
    }
    var fontStyle by remember {
        mutableStateOf(FontStyle.Normal)
    }
    var fontSize by remember {
        mutableStateOf(16.dp)
    }
    var fontWeight by remember {
        mutableStateOf(FontWeight.Normal)
    }

    val localDensity = LocalDensity.current
    var componentHeight by remember { mutableStateOf(0.dp) }
    val animatedComponentHeight by animateDpAsState(
        targetValue = componentHeight,
        label = "",
        animationSpec = tween(
            durationMillis = 800,
            easing = FastOutLinearInEasing
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        ) {

            Text(
                text = "What's the title ?",
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            TextField(
                value = title,
                onValueChange = { newTitle ->
                    title = newTitle
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .border(0.5.dp, color = Color.DarkGray, shape = ShapeDefaults.Small)
                    .weight(1f)
                    .clip(ShapeDefaults.Small),
                textStyle = TextStyle(
                    color = Color.Black
                ),
                colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Row {
                IconButton(
                    onClick = {
                        fontStyle =
                            if (fontStyle == FontStyle.Italic) FontStyle.Normal else FontStyle.Italic
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.FormatItalic,
                        contentDescription = "italic_format",
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = {
                        fontWeight =
                            if (fontWeight == FontWeight.Bold) FontWeight.Normal else FontWeight.Bold
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.FormatBold,
                        contentDescription = "bold_format",
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = {
                        fontSize = if (fontSize == 24.dp) 16.dp else 24.dp
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.FormatSize,
                        contentDescription = "size_format",
                        tint = Color.Black
                    )
                }
            }
            Divider(
                color = Color.DarkGray,
                modifier = Modifier
                    .height(24.dp)
                    .width(0.5.dp)
            )
            Row {
                IconButton(
                    onClick = {
                        cardBackground =
                            if (cardBackground == 0xFFFFFF00) 0xFFCCCFCF else 0xFFFFFF00
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Square,
                        modifier = Modifier.border(BorderStroke(0.5.dp, color = Color.Black)),
                        contentDescription = "Yellow",
                        tint = Color.Yellow
                    )
                }
                IconButton(
                    onClick = {
                        cardBackground =
                            if (cardBackground == 0xFF00FFFF) 0xFFCCCFCF else 0xFF00FFFF
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Square,
                        modifier = Modifier.border(BorderStroke(0.5.dp, color = Color.Black)),
                        contentDescription = "Cyan",
                        tint = Color.Cyan
                    )
                }
                IconButton(
                    onClick = {
                        cardBackground =
                            if (cardBackground == 0xFFFF6F00) 0xFFCCCFCF else 0xFFFF6F00
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Square,
                        modifier = Modifier.border(BorderStroke(0.5.dp, color = Color.Black)),
                        contentDescription = "Orange",
                        tint = Color(0xFFFF6F00),
                    )
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(animatedComponentHeight)
                        .border(0.5.dp, shape = ShapeDefaults.Medium, color = Color.Black)
                        .width(10.dp)
                        .clip(ShapeDefaults.Medium)
                        .background(
                            color = Color(
                                if (cardBackground == 0xFFCCCFCF) 0xFF888888 else cardBackground
                            )
                        )
                )
                TextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    modifier = Modifier
                        .weight(1f)
                        .border(0.5.dp, color = Color.DarkGray, shape = ShapeDefaults.Medium)
                        .clip(ShapeDefaults.Medium)
                        .onGloballyPositioned {
                            componentHeight = with(localDensity) { it.size.height.toDp() }
                        },
                    textStyle = TextStyle(
                        fontStyle = fontStyle,
                        fontWeight = fontWeight,
                        fontSize = fontSize.dpTextUnit
                    ),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Is important ?",
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
                )

                Checkbox(
                    checked = isImportant,
                    onCheckedChange = {
                        isImportant = !isImportant
                    }
                )
            }

            Button(
                onClick = {
                    onNavigateNotes()
                    viewModel.addNote(
                        Note(
                            title = title,
                            body = text,
                            isImportant = isImportant,
                            fontStyle = fontStyle != FontStyle.Normal,
                            fontSize = fontSize != 16.dp,
                            fontWeight = fontWeight != FontWeight.Normal,
                            background = cardBackground
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Text(text = "Save note", color = Color.White)
            }

        }
    }
}

val Dp.dpTextUnit: TextUnit
    @Composable
    get() = with(LocalDensity.current) { this@dpTextUnit.toSp() }