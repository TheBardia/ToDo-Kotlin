package com.example.todo_kotlin

data class ToDoItem (
        var action: String,
        var completed: Boolean = false,
        val id: Int) {
    override fun toString(): String {
        return "ID: $id - Action: $action - Completed: $completed"
    }
}