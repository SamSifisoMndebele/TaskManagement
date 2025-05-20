package com.example.taskmanangement

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    // Views
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddTask: Button
    private lateinit var btnDeleteCompleted: Button
    private lateinit var etTaskTitle: EditText
    private lateinit var etTaskDesc: EditText
    private lateinit var searchView: androidx.appcompat.widget.SearchView

    private val viewModelFactory by lazy {
        val taskDao = Room.databaseBuilder(this, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration(true)
            .build()
            .taskDao()
        TaskViewModelFactory(taskDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set layout without view binding
        setContentView(R.layout.activity_main)

        // Find views by ID
        recyclerView = findViewById(R.id.recyclerView)
        btnAddTask = findViewById(R.id.btnAddTask)
        btnDeleteCompleted = findViewById(R.id.btnDeleteCompleted)
        etTaskTitle = findViewById(R.id.etTaskTitle)
        etTaskDesc = findViewById(R.id.etTaskDesc)
        searchView = findViewById(R.id.searchView)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, viewModelFactory)[TaskViewModel::class.java]

        // Initialize Adapter
        adapter = TaskAdapter(mutableListOf(),
            onTaskClick = { task ->
                val newTask = task.copy(isCompleted = !task.isCompleted)
                viewModel.updateTask(newTask)

                Toast.makeText(this,
                    "Task ${if (newTask.isCompleted) "completed" else "marked incomplete"}",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onTaskLongClick = { task ->
                viewModel.deleteTask(task)
                Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
            }
        )

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe LiveData
        viewModel.allTasks.observe(this) { tasks ->
            adapter.updateList(tasks)
        }

        // Add Task button
        btnAddTask.setOnClickListener {
            val title = etTaskTitle.text.toString().trim()
            val description = etTaskDesc.text.toString().trim()

            if (title.isNotEmpty()) {
                val newTask = TodoTask(title = title, description = description, isCompleted = false)
                viewModel.addTask(newTask)
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()

                etTaskTitle.text.clear()
                etTaskDesc.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task title", Toast.LENGTH_SHORT).show()
            }
        }

        // Delete Completed button
        btnDeleteCompleted.setOnClickListener {
            viewModel.deleteCompletedTasks()
            Toast.makeText(this, "Completed tasks deleted", Toast.LENGTH_SHORT).show()
        }

        // SearchView filter
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val allTasks = viewModel.allTasks.value ?: emptyList()
                val filtered = if (newText.isNullOrBlank()) allTasks
                else allTasks.filter { it.title.contains(newText, ignoreCase = true) }
                adapter.updateList(filtered)
                return true
            }
        })
    }
}
