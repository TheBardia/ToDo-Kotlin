package com.example.todo_kotlin

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter(
        private val todos: MutableList<ToDoItem>
        ) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> (){

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_todo,
                        parent,
                        false
                )
        )
    }

    fun addToDo(todo: ToDoItem, ) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneToDos() {
        todos.removeAll { todo ->
            todo.completed
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToDoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = todos[position]
        holder.itemView.apply {
            tvToDoTitle.text = currentToDo.action
            cbDone.isChecked = currentToDo.completed
            toggleStrikeThrough(tvToDoTitle, currentToDo.completed)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvToDoTitle, isChecked)
                currentToDo.completed = !currentToDo.completed
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}