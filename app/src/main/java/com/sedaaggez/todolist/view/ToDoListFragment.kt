package com.sedaaggez.todolist.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.sedaaggez.todolist.R
import com.sedaaggez.todolist.adapter.ToDoAdapter
import com.sedaaggez.todolist.model.ToDoItem
import kotlinx.android.synthetic.main.fragment_to_do_list.*
import java.util.*

class ToDoListFragment : Fragment() {

    lateinit var database: DatabaseReference
    private val toDoAdapter = ToDoAdapter(arrayListOf())
    var toDoList: MutableList<ToDoItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().reference

        floatingActionButton.setOnClickListener {
            showDialog(requireContext())
        }


        recyclerViewToDo.layoutManager = LinearLayoutManager(context)
        recyclerViewToDo.adapter = toDoAdapter

        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                addItem(snapshot)
            }
        })
    }

    private fun addItem(snapshot: DataSnapshot) {
        toDoList = mutableListOf()
        val items = snapshot.children.iterator()
        if (items.hasNext()) {
            val toDoIndexedValue = items.next()
            val itemsIterator = toDoIndexedValue.children.iterator()
            while (itemsIterator.hasNext()) {
                val currentItem = itemsIterator.next()
                val map = currentItem.value as HashMap<String, Any>
                val item = ToDoItem(currentItem.key.toString(), map["title"].toString(), map["description"].toString(), map["completed"] as Boolean)
                toDoList!!.add(item)
            }
        }
        toDoAdapter.updateToDoList(toDoList!!)
    }

    private fun showDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_add_to_do_item)
        val editTextItemTitle = dialog.findViewById(R.id.editTextItemTitle) as EditText
        val editTextItemDescription =
            dialog.findViewById(R.id.editTextItemDescription) as EditText
        val buttonSave = dialog.findViewById(R.id.buttonSave) as Button
        val buttonCancel = dialog.findViewById(R.id.buttonCancel) as Button
        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
        buttonSave.setOnClickListener {
            val newItem = database.child("toDo").push()
            val item = ToDoItem(newItem.key.toString(), editTextItemTitle.text.toString(), editTextItemDescription.text.toString(), false)
            newItem.setValue(item)
            dialog.dismiss()
        }
        dialog.show()
    }

}