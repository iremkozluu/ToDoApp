package com.ikozlu.todoapp.data.model

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    var priority: String
)
