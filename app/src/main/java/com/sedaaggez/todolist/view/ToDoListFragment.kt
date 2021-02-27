package com.sedaaggez.todolist.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.sedaaggez.todolist.R
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class ToDoListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatingActionButton.setOnClickListener {
            showDialog(requireContext())
        }
    }


    private fun showDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_add_to_do_item)
        val editTextItemTitle = dialog.findViewById(R.id.editTextItemTitle) as EditText
        val editTextItemDescription = dialog.findViewById(R.id.editTextItemDescription) as EditText
        val buttonSave = dialog.findViewById(R.id.buttonSave) as Button
        val buttonCancel = dialog.findViewById(R.id.buttonCancel) as Button
        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
        buttonSave.setOnClickListener {
            println(editTextItemDescription.text)
            println(editTextItemTitle.text)
            dialog.dismiss()
        }
        dialog.show()

    }

}