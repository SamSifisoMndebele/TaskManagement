# Task Management App

## Project Brief

This project is a simple Task Management System developed as part of an onboarding task for Dudu and Masuku (D&M) Software Development Company. The application allows users to add, display, delete, and manage tasks. It is built using Android and Kotlin, incorporating object-oriented programming, event handling, and basic multimedia features.

## Features Implemented

This application implements the following features based on the project brief:

**1.1) Project Setup and UI Design (10)**
    *   Basic UI designed in Android Kotlin.
    *   An `EditText` for entering task titles.
    *   An `EditText` for entering task descriptions.
    *   A `RecyclerView` for displaying the list of tasks.
    *   A Button to add new tasks.
    *   A Button to delete completed tasks.
    *   A `SearchView` to filter tasks by title.

**1.2) Implementing an Object-Oriented Approach (15)**
    *   `TodoTask` data class with properties for `title`, `description`, and `isCompleted` status.
    *   `TaskViewModel` to manage task data, business logic, and interaction with the data source.
    *   `TaskAdapter` to bind task data to the `RecyclerView`.
    *   `TaskDao` (Data Access Object) for Room database interactions.
    *   `TaskDatabase` as the Room database instance.
    *   `TaskViewModelFactory` for creating `TaskViewModel` instances with dependencies.

**1.3) Implementing Event Handling in Android (15)**
    *   Click event listener on the "Add Task" button to handle task addition.
    *   Click event listener on individual tasks in the `RecyclerView` to mark a task as completed or incomplete.
    *   Long-press event listener on individual tasks in the `RecyclerView` to delete tasks.

**1.4) Basic Graphics and Multimedia Support (15)**
    *   An image (checkbox icon) next to each task in the `RecyclerView` to indicate its completion status.
    *   Toast message displayed when a task is added.
    *   Toast message displayed when a task is deleted.
    *   Toast message displayed when a task is marked as completed or incomplete.
    *   Toast message displayed when completed tasks are deleted.
    *   *(Note: Sound notification on task addition was not explicitly implemented in the provided code snippets but can be added.)*

**1.5) Developing a Network-Supported Feature (15)**
    *   *(This feature is not present in the provided code snippets. If implemented, describe how you used HttpURLConnection or Retrofit with Kotlin Coroutines to fetch task suggestions from an API.)*
    *   **Example (if implemented):** "Used Retrofit with Kotlin Coroutines to fetch random task suggestions from the 'Bored API' when the user needs inspiration."

**1.6) Sending Emails and SMS Notifications (15)**
    *   *(This feature is not present in the provided code snippets. If implemented, describe the email sending and SMS reminder functionality.)*
    *   **Example (if implemented):** "Implemented a feature to send an email reminder if a task is overdue for more than 24 hours (placeholder - actual email sending logic might require backend or specific intent handling). Implemented a placeholder for an SMS reminder for incomplete tasks."

**1.7) Implementing Database Features (15)**
    *   Tasks are stored using the Room Persistence Library.
    *   *(Dark Mode support was not explicitly in the provided Java/Kotlin files. If implemented via themes.xml or programmatically, mention it here.)*
    *   A search bar (`SearchView`) is implemented to filter tasks by their title.

## How to Build and Run

1.  **Clone the repository:**
