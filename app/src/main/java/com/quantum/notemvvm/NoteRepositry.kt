package com.quantum.notemvvm

import androidx.lifecycle.LiveData

class NoteRepositry (private val notesDao:noteDAO) {

    val allNotes: LiveData<List<NoteModel>> =notesDao.getAllNotes()

    suspend fun insert(note: NoteModel){
        notesDao.insert(note)
    }

    suspend fun delete(note: NoteModel){
        notesDao.delete(note)
    }

    suspend fun update(note: NoteModel){
        notesDao.update(note)
    }
}