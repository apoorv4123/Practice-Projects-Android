package com.example.todolist1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist1.adapters.TodoAdapter
import com.example.todolist1.models.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val todos: ArrayList<Todo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodo.layoutManager = LinearLayoutManager(this)    // set the LayoutManager of recyclerView
        val todoAdapter: TodoAdapter = TodoAdapter(todos)   // construct the adapter
        rvTodo.adapter = todoAdapter    // set the adapter for recycler view

        btnAdd.setOnClickListener {
            val newTask = etNewTodo.text.toString()
            todos.add(Todo(newTask, false))
            todoAdapter.notifyDataSetChanged()

            Toast.makeText(this, "New Task: " + newTask, Toast.LENGTH_SHORT).show()
        }
    }
}