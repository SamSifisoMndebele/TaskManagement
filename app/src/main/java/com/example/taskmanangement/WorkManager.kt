package com.example.taskmanangement

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

// Worker for sending overdue notifications
class OverdueTaskNotifierWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        // 1. Fetch overdue tasks from Room
        // 2. For each overdue task, prepare and send an email/notification
        // 3. Return Result.success() or Result.failure()/Result.retry()
        return Result.success()
    }
}

class TaskScheduler(private val applicationContext: Context) {
    companion object {
        const val OVERDUE_TASK_CHECK_WORK_NAME = "overdueTaskCheck"
        const val REPEAT_INTERVAL_DAYS = 1L // Use L for Long
        private const val TAG = "TaskScheduler" // For logging
    }
    fun scheduleOverdueTaskChecks() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<OverdueTaskNotifierWorker>(
            REPEAT_INTERVAL_DAYS, TimeUnit.DAYS
        )
            .setConstraints(constraints)
            .setInitialDelay(5, TimeUnit.MINUTES)
            .build()

        try {
            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                OVERDUE_TASK_CHECK_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )
            Log.i(TAG, "Successfully scheduled overdue task checks.")
        } catch (e: Exception) {
            // While WorkManager enqueue operations are generally safe,
            // catching unexpected issues can be good practice.
            Log.e(TAG, "Error scheduling overdue task checks", e)
        }
    }
}
