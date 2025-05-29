package za.co.taskmanagement.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE title LIKE :title OR " +
            "description LIKE :description")
    fun findByTitle(title: String, description: String = ""): Task

    @Insert
    fun insert(vararg users: Task)

    @Delete
    fun delete(user: Task)
}