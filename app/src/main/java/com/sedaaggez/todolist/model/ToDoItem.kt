package com.sedaaggez.todolist.model


data class ToDoItem(
        var UID: String,
        var title: String,
        var description: String,
        var completed: Boolean
)