# Task Management App

## Project Brief

As an enterprise software developer, I have joined Dudu and Masuku (D&M) Software Development Company. The University of Mpumalanga has contracted our company to manage the institution's information system implementation. In my first task on my first day of work at the company, I am required to develop a simple Task Management System that the institution's students can use to manage their simple daily tasks. I am required to develop a simple Task Management App using Android and Kotlin. The app should allow users to add, display, and delete tasks. The application should incorporate object-oriented programming, event handling, networking, and multimedia features.

The table below highlights what must be developed based on the above brief:

| Section | Feature Description                                                                | Points |
|:--------|:-----------------------------------------------------------------------------------|:-------|
| 1.1)    | **Project Setup and UI Design**                                                    | (10)   |
|         | - Design a basic UI in Android Kotlin with:                                        |        |
|         | - An EditText for entering tasks.                                                  |        |
|         | - A RecyclerView for displaying tasks.                                             |        |
|         | - A Button to add new tasks.                                                       |        |
|         | - A Button to delete completed tasks.                                              |        |
| 1.2)    | **Implementing an Object-Oriented Approach**                                       | (15)   |
|         | - Create a Task class with properties (title, description, and completion status). |        |
|         | - Implement a TaskManager class that manages task storage and retrieval.           |        |
|         | - Use a ViewModel for data persistence.                                            |        |
| 1.3)    | **Implementing Event Handling in Android**                                         | (15)   |
|         | - Add a click event listener to handle task addition.                              |        |
|         | - Add an event listener for marking a task as completed.                           |        |
|         | - Implement a long-press listener to delete tasks.                                 |        |
| 1.4)    | **Basic Graphics and Multimedia Support**                                          | (15)   |
|         | - Add an image next to each task to indicate completion status.                    |        |
|         | - Play a notification sound when a task is added.                                  |        |
|         | - Display a toast message when a task is deleted.                                  |        |
| 1.5)    | **Developing a Network-Supported Feature**                                         | (15)   |
|         | - Implement AsyncTask or Kotlin Coroutines to fetch task suggestions from an API.  |        |
|         | - Use HttpURLConnection or Retrofit to send a request and display the response.    |        |
| 1.6)    | **Sending Emails and SMS Notifications**                                           | (15)   |
|         | - Implement a feature to send an email when a task is overdue.                     |        |
|         | - Implement a feature to send an SMS reminder for incomplete tasks. (4)            |        |
| 1.7)    | **Implementing Database Features**                                                 | (15)   |
|         | - Store tasks using the Room Database for persistence. (3)                         |        |
|         | - Implement Dark Mode support for the app. (3)                                     |        |
|         | - Add a search bar to filter tasks by title. (3)                                   |        |


## Features Implemented

This application aims to implement the following features based on the project brief:

**1.1) Project Setup and UI Design (10/10)**
*   **[X] Basic UI designed in Android Kotlin.** (Implemented in `activity_main.xml`)
*   **[X] An `EditText` for entering task titles.** (ID: `etTaskTitle` in `activity_main.xml`)
*   **[X] An `EditText` for entering task descriptions.** (ID: `etTaskDesc` in `activity_main.xml`)
*   **[X] A `RecyclerView` for displaying the list of tasks.** (ID: `recyclerView` in `activity_main.xml`)
*   **[X] A Button to add new tasks.** (ID: `btnAddTask` in `activity_main.xml`)
*   **[X] A Button to delete completed tasks.** (ID: `btnDeleteCompleted` in `activity_main.xml`)
*   **[X] A `SearchView` to filter tasks by title.** (ID: `searchView` in `activity_main.xml`)

**1.2) Implementing an Object-Oriented Approach (15/15)**
*   **[X] `TodoTask` data class with properties for `title`, `description`, and `isCompleted` status.** (Defined in `TodoTask.kt`)
*   **[X] `TaskViewModel` to manage task data, business logic, and interaction with the data source.** (Represents the TaskManager concept, defined in `TaskViewModel.kt`)
*   **[X] `TaskAdapter` to bind task data to the `RecyclerView`.** (Defined in `TaskAdapter.kt`)
*   **[X] `TaskDao` (Data Access Object) for Room database interactions.** (Defined in `TaskDao.kt`)
*   **[X] `TaskDatabase` as the Room database instance.** (Defined in `TaskDatabase.kt`)
*   **[X] `TaskViewModelFactory` for creating `TaskViewModel` instances with dependencies.** (Defined in `TaskViewModelFactory.kt`)

**1.3) Implementing Event Handling in Android (15/15)**
*   **[X] Click event listener on the "Add Task" button to handle task addition.** (Implemented in `MainActivity.kt` for `btnAddTask`)
*   **[X] Click event listener on individual tasks in the `RecyclerView` to mark a task as completed or incomplete.** (Implemented in `TaskAdapter.kt` via `onTaskClick` lambda)
*   **[X] Long-press event listener on individual tasks in the `RecyclerView` to delete tasks.** (Implemented in `TaskAdapter.kt` via `onTaskLongClick` lambda)

**1.4) Basic Graphics and Multimedia Support (15/15)**
*   **[X] An image (checkbox icon) next to each task in the `RecyclerView` to indicate its completion status.** (Implemented in `TaskAdapter.kt` using `android.R.drawable.checkbox_on_background` and `checkbox_off_background`)
*   **[X] Play a notification sound when a task is added.** (Implemented in `MainActivity.kt` using `MediaPlayer`)
*   **[X] Display a toast message when a task is added.** (Implemented in `MainActivity.kt`)
*   **[X] Display a toast message when a task is deleted.** (Implemented in `MainActivity.kt` and `TaskAdapter.kt`)
*   **[X] Display a toast message when a task is marked as completed or incomplete.** (Implemented in `MainActivity.kt`)
*   **[X] Display a toast message when completed tasks are deleted.** (Implemented in `MainActivity.kt`)

**1.5) Developing a Network-Supported Feature (0/15)**
*   **[ ] Implement AsyncTask or Kotlin Coroutines to fetch task suggestions from an API.** *(Not implemented in the provided code. This would require network libraries like Retrofit or Ktor and API integration.)*
*   **[ ] Use HttpURLConnection or Retrofit to send a request and display the response.** *(Not implemented.)*
*   **Example (if implemented):** "Used Retrofit with Kotlin Coroutines to fetch random task suggestions from the 'Bored API' when the user needs inspiration."

**1.6) Sending Emails and SMS Notifications (0/15)**
*   **[ ] Implement a feature to send an email when a task is overdue.** *(Not implemented. This would typically involve `Intent.ACTION_SEND` for email clients or backend integration.)*
*   **[ ] Implement a feature to send an SMS reminder for incomplete tasks.** *(Not implemented. This would typically involve `Intent.ACTION_SENDTO` for SMS or backend integration, and requires careful permission handling.)*

**1.7) Implementing Database Features (10/15)**
*   **[X] Store tasks using the Room Database for persistence.** (3/3) (Implemented via `TaskDatabase.kt`, `TaskDao.kt`, and usage in `TaskViewModel.kt`)
*   **[ ] Implement Dark Mode support for the app.** (0/3) *(Not explicitly implemented in the provided Kotlin/Java files. This is usually handled via `themes.xml` (e.g., inheriting from a `Theme.MaterialComponents.DayNight` theme) and potentially dynamic theme switching.)*
*   **[X] Add a search bar to filter tasks by title.** (3/3) (Implemented in `MainActivity.kt` using `SearchView` and filtering logic within its `OnQueryTextListener`)

## How to Build and Run

1.  **Clone the Repository:**
    To get a local copy of this project, you'll need to clone its Git repository.
    *   HTTPS URL: `https://github.com/SamSifisoMndebele/TaskManagement.git`
    *   SSH URL: `git@github.com:SamSifisoMndebele/TaskManagement.git`
