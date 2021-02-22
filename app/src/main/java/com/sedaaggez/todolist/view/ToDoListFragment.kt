package com.sedaaggez.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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

        button.setOnClickListener{
            val action = ToDoListFragmentDirections.actionToDoListFragmentToToDoItemFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}