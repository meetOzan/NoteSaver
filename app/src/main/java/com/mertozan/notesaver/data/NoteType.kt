package com.mertozan.notesaver.data

import android.os.Parcelable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NoteType(
    var fontStyle: @RawValue androidx.compose.ui.text.font.FontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
    var fontSize: @RawValue Dp = 16.dp,
    var fontWeight: @RawValue FontWeight? = FontWeight.Normal,
) : Parcelable
