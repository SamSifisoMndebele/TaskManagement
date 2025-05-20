package com.example.taskmanangement

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TodoTask)

    @Update
    suspend fun update(task: TodoTask)

    @Delete
    suspend fun delete(task: TodoTask)

    @Query("DELETE FROM TodoTask WHERE isCompleted = 1")
    suspend fun deleteCompleted()

    @Query("SELECT * FROM TodoTask ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<TodoTask>>
}
