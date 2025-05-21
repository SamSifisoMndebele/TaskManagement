package com.example.taskmanangement

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.taskmanangement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    private val viewModelFactory by lazy {
        val taskDao = Room.databaseBuilder(this, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration(true)
            .build()
            .taskDao()
        TaskViewModelFactory(taskDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        with(binding) {
            // Setup RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter

            // Observe LiveData
            viewModel.allTasks.observe(this@MainActivity) { tasks ->
                adapter.updateList(tasks)
            }

            // Add Task button
            btnAddTask.setOnClickListener {
                val title = etTaskTitle.text.toString().trim()
                val description = etTaskDesc.text.toString().trim()

                if (title.isNotEmpty()) {
                    val newTask = TodoTask(title = title, description = description, isCompleted = false)
                    viewModel.addTask(newTask)
                    Toast.makeText(this@MainActivity, "Task added", Toast.LENGTH_SHORT).show()

                    etTaskTitle.text.clear()
                    etTaskDesc.text.clear()
                } else {
                    Toast.makeText(this@MainActivity, "Please enter a task title", Toast.LENGTH_SHORT).show()
                }
            }

            // Delete Completed button
            btnDeleteCompleted.setOnClickListener {
                viewModel.deleteCompletedTasks()
                Toast.makeText(this@MainActivity, "Completed tasks deleted", Toast.LENGTH_SHORT).show()
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
}
