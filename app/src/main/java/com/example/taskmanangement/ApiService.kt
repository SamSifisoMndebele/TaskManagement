package com.example.taskmanangement

import retrofit2.http.GET

data class TaskSuggestion(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

interface ApiService {
    @GET("api/activity") // Endpoint for Bored API
    suspend fun getRandomTaskSuggestion(): TaskSuggestion // Define response data class
}
