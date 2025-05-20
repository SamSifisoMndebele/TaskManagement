package com.example.taskmanangement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)
