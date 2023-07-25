package com.mertozan.notesaver.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertozan.notesaver.data.Note
import com.mertozan.notesaver.data.NoteDao

@Database(entities = [(Note::class)], version = 3, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}
