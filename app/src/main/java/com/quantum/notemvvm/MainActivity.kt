package com.quantum.notemvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterFace, NoteClickInterFace {

    lateinit var  recyclerView:RecyclerView
    lateinit var  fab:FloatingActionButton
    lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.idRVNotes)
        fab=findViewById(R.id.fabAddNote)

        recyclerView.layoutManager=LinearLayoutManager(this)

        val adapter=NoteAdapter(this,this,this)
        recyclerView.adapter=adapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNote.observe(this, Observer { list ->

            list?.let{
                adapter.updateList(it)
            }

        })

        fab.setOnClickListener {
            val intent= Intent (this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }


    }

    override fun onDeleteIconClicked(note: NoteModel) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} deleted",Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClicked(note: NoteModel) {
        val intent= Intent (this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteId",note.id)


        startActivity(intent)
        this.finish()
    }
}