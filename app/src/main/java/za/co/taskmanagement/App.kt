package za.co.taskmanagement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import za.co.taskmanagement.db.Task

@Composable
fun App() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("New Task Title")
            },
            placeholder = {
                Text("Type the task title")
            },
            value = title,
            onValueChange = {
                title = it
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("New Task Description")
            },
            placeholder = {
                Text("Type the task description")
            },
            value = description,
            onValueChange = {
                description = it
            }
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = title.isNotBlank(),
            onClick = {
                val task = Task(
                    title = title,
                    description = description,
                    isCompleted = false
                )
                tasks = tasks + task
                title = ""
                description = ""
            }
        ) {
            Text("Add New Task")
        }

        LazyColumn {
            items(tasks) { task ->
                TaskItem(task) { isCompleted ->
                    tasks = tasks.map {
                        if (it == task) it.copy(isCompleted = isCompleted)
                        else it
                    }
                }
            }
        }

        if (tasks.any { it.isCompleted }) {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    tasks = tasks.filterNot { it.isCompleted }
                }
            ) {
                Text("Delete Completed Task")
            }
        }
    }
}