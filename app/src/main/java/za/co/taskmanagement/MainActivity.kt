package za.co.taskmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import za.co.taskmanagement.db.TaskDatabase
import za.co.taskmanagement.ui.theme.TaskManagementTheme

class MainActivity : ComponentActivity() {

    private val taskDao by lazy {
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, "task-database"
        ).build()
        db.taskDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagementTheme {
                App()
            }
        }
    }
}