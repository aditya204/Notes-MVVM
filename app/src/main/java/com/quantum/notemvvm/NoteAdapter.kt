package com.quantum.notemvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets.Side.all
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class NoteAdapter (
    val cotext: Context,
    val noteClickDeleteInterFace: NoteClickDeleteInterFace,
    val noteClickInterFace: NoteClickInterFace

) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {





   private val allNotes=ArrayList<NoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.notes_recycler_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTv.setText(allNotes.get(position).noteTitle)
        holder.timeTv.setText("last Updated :" +allNotes.get(position).timeStamp)

        holder.deleteIv.setOnClickListener{
            noteClickDeleteInterFace.onDeleteIconClicked(allNotes.get(position))

        }

        holder.itemView.setOnClickListener {
            noteClickInterFace.onNoteClicked(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return  allNotes.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val noteTv=itemView.findViewById<TextView>(R.id.item_title)
        val timeTv=itemView.findViewById<TextView>(R.id.item_timestamp)
        val deleteIv=itemView.findViewById<ImageView>(R.id.item_delete_iv)
    }

    fun updateList(newlist :List<NoteModel>){
        allNotes.clear()
        allNotes.addAll(newlist)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterFace{
    fun onDeleteIconClicked(note: NoteModel)
}

interface NoteClickInterFace{
    fun onNoteClicked(note: NoteModel)
}