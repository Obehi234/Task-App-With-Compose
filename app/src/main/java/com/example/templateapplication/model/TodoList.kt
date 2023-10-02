package com.example.templateapplication.model

private val todoList =  listOf(
        Todo(1, false, "Gym", "You should be at the gym at 7 and remember to get Bread on your way"),
        Todo(2, false, "Gym", "You should be at the gym at 7 and remember to get Bread on your way"),
        Todo(2, false, "Hair Appointment", "You should be at the gym at 7 and remember to get Bread on your way"),
    )


data class TodoList(
    val list: List<Todo> = todoList
)

data class Todo(
    val id: Int,
    val status: Boolean,
    val todoName: String,
    val todoDescription: String
)
