package za.co.taskmanagement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import za.co.taskmanagement.db.Task

@Composable
fun TaskItem(
    task: Task,
    onTaskClicked: (Boolean) -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                onTaskClicked(!task.isCompleted)
            }
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onTaskClicked
            )
            Column {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaskItemPreview() {
    val task = Task(
        title = "Task Title",
        description = "Task Description",
        isCompleted = false
    )
    TaskItem(task = task)
}