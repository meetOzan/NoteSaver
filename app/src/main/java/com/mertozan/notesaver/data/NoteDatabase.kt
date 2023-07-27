package com.mertozan.notesaver.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Note::class)], version = 7, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}
