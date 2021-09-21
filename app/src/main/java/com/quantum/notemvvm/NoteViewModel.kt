package com.quantum.notemvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application ) :AndroidViewModel(application) {

    internal val allNote:LiveData<List<NoteModel>>
    val repositry:NoteRepositry

    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        repositry= NoteRepositry(dao)
        allNote=repositry.allNotes
    }

    fun deleteNote(note:NoteModel) =viewModelScope.launch ( Dispatchers.IO ){
        repositry.delete(note)
    }

    fun updateNote(note:NoteModel) =viewModelScope.launch ( Dispatchers.IO ){
        repositry.update(note)
    }

    fun addNote(note:NoteModel) =viewModelScope.launch ( Dispatchers.IO ){
        repositry.insert(note)
    }
}