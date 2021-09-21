package com.quantum.notemvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdit:EditText
    lateinit var noteDscriptinEdit:EditText
    lateinit var updateBtn:Button
    lateinit var  viewModel: NoteViewModel
    var noteId=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEdit=findViewById(R.id.et_title)
        noteDscriptinEdit=findViewById(R.id.et_note_descriptin)
        updateBtn=findViewById(R.id.btn_add_update)

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val noteType=intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDescription=intent.getStringExtra("noteDescription")
            val noteId=intent.getIntExtra("noteId",-1)

            updateBtn.setText("Update Note")
            noteTitleEdit.setText(noteTitle)
            noteDscriptinEdit.setText(noteDescription)

        }else{
            updateBtn.setText("Save Note")


        }

        updateBtn.setOnClickListener{
            val title=noteTitleEdit.text.toString()
            val desc=noteDscriptinEdit.text.toString()

            if(noteType.equals("Edit")){

                if(title.isNotEmpty() && desc.isNotEmpty()){

                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote=NoteModel(title,desc,currentDate)
                    updateNote.id=noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated" ,Toast.LENGTH_SHORT).show()
                }


            }else{

                if(title.isNotEmpty() && desc.isNotEmpty()){

                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val newNote=NoteModel(title,desc,currentDate)

                    viewModel.addNote(newNote)
                    Toast.makeText(this,"Note Added" ,Toast.LENGTH_SHORT).show()
                }

            }

            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()



        }



    }
}