package com.mertozan.notesaver.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "notes")
data class Note(

    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id: Int,

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name= "note_body")
    val body: String,

    @ColumnInfo(name = "is_Important")
    val isImportant: Boolean,
/*
    @ColumnInfo(name = "color")
    val color: Int*/
): Parcelable
