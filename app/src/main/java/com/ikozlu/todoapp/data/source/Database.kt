package com.ikozlu.todoapp.data.source

import com.ikozlu.todoapp.data.model.Note

object Database {

    private val dailyNotes = mutableListOf<Note>()

    fun getDailyNotes(): List<Note>{
        return dailyNotes
    }

    fun addDailyNotes(title: String, description: String, priority: String){
        var newNote = Note(
            id = (dailyNotes.lastOrNull()?.id ?: 0) + 1,
            title = title,
            description = description,
            priority = priority
        )
        dailyNotes.add(newNote)

    }

    fun deleteData(note : Note){
        dailyNotes.remove(note)
    }

}