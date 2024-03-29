package com.quantum.notemvvm

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface noteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:NoteModel)

    @Update
    suspend fun update(note:NoteModel)

    @Delete
    suspend fun  delete(note: NoteModel)

    @Query("Select* from notesTable order by id ASC")
    fun getAllNotes():LiveData<List<NoteModel>>
}