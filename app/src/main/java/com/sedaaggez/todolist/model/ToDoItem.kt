package com.sedaaggez.todolist.model


data class ToDoItem(
        var id: Int,
        var title: String,
        var description: String,
        var completed: Boolean
)