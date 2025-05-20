package com.example.taskmanangement

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
