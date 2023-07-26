package com.mertozan.notesaver.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id: Int = 0,

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_body")
    val body: String,

    @ColumnInfo(name = "is_Important")
    val isImportant: Boolean,

    /*
        @ColumnInfo(name = "card_color")
        val color : @RawValue Color = Color.White,

        @ColumnInfo(name = "text_style")
        val textStyle : NoteType? = null
     */

) : Parcelable
