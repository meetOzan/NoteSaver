package com.mertozan.composesaver.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mertozan.notesaver.data.Note
import com.mertozan.composesaver.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    val allNotes : LiveData<List<Note>> = repository.allNotes
    val importantNotes : LiveData<List<Note>> = repository.importantNotes

    fun addNote(newNote: Note){
        repository.addNote(newNote)
    }

    fun deleteNote(note: Note){
        repository.deleteNote(note)
    }

    fun updateNote(note: Note){
        repository.updateNote(note)
    }

}