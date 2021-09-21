package com.quantum.notemvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = arrayOf(NoteModel::class),version = 1,exportSchema = false)
abstract  class NoteDatabase:RoomDatabase() {

    abstract  fun getNoteDao():noteDAO

    companion object{

        @Volatile
        private  var INSTANCE:NoteDatabase?=null

        fun getDatabase(context:Context) :NoteDatabase{
            return INSTANCE?:synchronized(this){
                val instace= Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                     "note_database"
                ).build()
                INSTANCE =instace
                instace
            }

        }
    }
}