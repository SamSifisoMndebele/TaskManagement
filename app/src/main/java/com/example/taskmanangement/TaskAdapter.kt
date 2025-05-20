package com.example.taskmanangement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView



class TaskAdapter(
    private var tasks: MutableList<TodoTask>,
    private val onTaskClick: (TodoTask) -> Unit,
    private val onTaskLongClick: (TodoTask) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDesc)
        private val imgStatus: ImageView = itemView.findViewById(R.id.imgStatus)

        fun bind(task: TodoTask) {
            tvTitle.text = task.title
            tvDescription.text = task.description

            imgStatus.setImageResource(
                if (task.isCompleted)
                    android.R.drawable.checkbox_on_background
                else
                    android.R.drawable.checkbox_off_background
            )

            itemView.setOnClickListener {
                onTaskClick(task)
            }

            itemView.setOnLongClickListener {
                onTaskLongClick(task)
                Toast.makeText(it.context, "Task deleted", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)  // Make sure this matches your XML layout name!
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    fun updateList(newTasks: List<TodoTask>) {
        tasks = newTasks.toMutableList()
        notifyDataSetChanged()
    }
}
