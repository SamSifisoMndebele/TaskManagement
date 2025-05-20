package com.example.taskmanangement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val allTasks = taskDao.getAllTasks()

    fun addTask(task: TodoTask) = viewModelScope.launch {
        taskDao.insert(task)
    }

    fun updateTask(task: TodoTask) = viewModelScope.launch {
        taskDao.update(task)
    }

    fun deleteTask(task: TodoTask) = viewModelScope.launch {
        taskDao.delete(task)
    }

    fun deleteCompletedTasks() = viewModelScope.launch {
        taskDao.deleteCompleted()
    }
}
