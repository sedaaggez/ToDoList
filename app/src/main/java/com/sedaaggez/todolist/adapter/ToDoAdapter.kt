package com.sedaaggez.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.todolist.R
import com.sedaaggez.todolist.model.ToDoItem
import kotlinx.android.synthetic.main.item_to_do.view.*

class ToDoAdapter(val toDoItemList: ArrayList<ToDoItem>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_to_do, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return toDoItemList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDo = toDoItemList[position]
        holder.itemView.textViewItemTitle.text = toDo.title
        holder.itemView.textViewItemDescription.text = toDo.description
        holder.itemView.checkBoxItem.isChecked = toDo.completed

    }

    fun updateToDoList(newToDoList: List<ToDoItem>) {
        toDoItemList.clear()
        toDoItemList.addAll(newToDoList)
        notifyDataSetChanged()
    }
}