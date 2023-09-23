package com.ikozlu.todoapp.dailynotes

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ikozlu.todoapp.R
import com.ikozlu.todoapp.common.viewBinding
import com.ikozlu.todoapp.data.source.Database
import com.ikozlu.todoapp.databinding.DialogAddNoteBinding
import com.ikozlu.todoapp.databinding.FragmentDailyNotesBinding


class DailyNotesFragment : Fragment(R.layout.fragment_daily_notes) {

    private val binding by viewBinding(FragmentDailyNotesBinding::bind)

    private val dailyNotesAdapter = DailyNotesAdapter(onNoteClick = ::onNoteClick)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            rvDailyNotes.adapter = dailyNotesAdapter
            dailyNotesAdapter.updateList(Database.getDailyNotes())

            fabAdd.setOnClickListener{
                showAddDialog()
            }

        }

    }
    private fun onNoteClick(desc: String){
        Toast.makeText(requireContext(), desc, Toast.LENGTH_SHORT).show()
    }

    private fun showAddDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = DialogAddNoteBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)
        val dialog = builder.create()


        with(dialogBinding){

            btnAddNote.setOnClickListener {
                val title = etTitle.text.toString()
                val desc = etDesc.text.toString()

                val priority = when {
                    rbLow.isChecked -> "Low"
                    rbMedium.isChecked -> "Medium"
                    rbHigh.isChecked -> "High"
                    else -> ""
                }
                if(title.isNotEmpty() && priority.isNotEmpty()){
                    Database.addDailyNotes(title, desc, priority)
                    dailyNotesAdapter.updateList(Database.getDailyNotes())
                    dialog.dismiss()
                }
                else{
                    Toast.makeText(requireContext(), "Please fill in the blanks!", Toast.LENGTH_SHORT).show()
                }
            }



        }

        dialog.show()
    }

}

//private operator fun Boolean.invoke(value: Any) {}

