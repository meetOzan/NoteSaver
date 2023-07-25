package com.mertozan.composesaver.repository

import androidx.lifecycle.MutableLiveData
import com.mertozan.notesaver.data.NoteDao
import com.mertozan.notesaver.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {

    val allNotes = MutableLiveData<List<Note>>()
    val importantNotes = MutableLiveData<List<Note>>()

    fun addNote(newNote : Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.addNote(newNote)
        }
    }

    fun deleteNote(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteNote(note)
        }
    }

    fun updateNote(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.updateNote(note)
        }
    }

}