package com.mertozan.notesaver.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mertozan.notesaver.data.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // onConflict means; Just add what I gave, don't replace or anything.
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes() : List<Note>

    @Query("SELECT * FROM notes WHERE is_Important = 1")
    fun getImportants() : List<Note>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}