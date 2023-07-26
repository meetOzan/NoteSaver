package com.mertozan.notesaver.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.composesaver.repository.NoteRepository
import com.mertozan.notesaver.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val allNotes: LiveData<List<Note>> = repository.allNotes

    fun getAllNote() {
        repository.getAllNotes()
    }

    fun addNote(newNote: Note) = viewModelScope.launch {
        repository.addNote(newNote)
        getAllNote()
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
        getAllNote()
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
        getAllNote()
    }

}