package com.ikozlu.todoapp.dailynotes

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikozlu.todoapp.data.model.Note
import com.ikozlu.todoapp.data.source.Database
import com.ikozlu.todoapp.databinding.ItemDailyNoteBinding

class DailyNotesAdapter(
    private val onNoteClick: (String) -> Unit
) :RecyclerView.Adapter<DailyNotesAdapter.DailyNoteViewHolder>() {

    private val noteList = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNoteViewHolder {
        val binding = ItemDailyNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyNoteViewHolder(binding, onNoteClick)
    }

    override fun onBindViewHolder(holder: DailyNoteViewHolder, position: Int){
        holder.bind(noteList[position])
    }

    inner class DailyNoteViewHolder(
        private val binding: ItemDailyNoteBinding,
        private val onNoteClick: (String) -> Unit

    ):RecyclerView.ViewHolder(binding.root){


        fun bind(note: Note){
            with(binding){
                tvTitle.text = note.title
                tvDesc.text = note.description
                tvPriority.text = note.priority

                checkBox.setOnClickListener {

                    val position = adapterPosition
                    notifyItemRemoved(position)
                    val removeAtList = noteList.removeAt(position)
                    Database.deleteData(removeAtList)

                }


               // root.setOnClickListener{
                 //   onNoteClick(note.description)
               // }

                when (note.priority) {
                    "High" -> cardView.setCardBackgroundColor(Color.RED)
                    "Medium" -> cardView.setCardBackgroundColor(Color.BLUE)
                    "Low" -> cardView.setCardBackgroundColor(Color.GREEN)
                    else -> cardView.setCardBackgroundColor(Color.WHITE)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateList(list: List<Note>){
        noteList.clear()
        noteList.addAll(list)
        notifyItemRangeChanged(0,list.size)
    }

}