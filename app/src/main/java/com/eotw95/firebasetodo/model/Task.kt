package com.eotw95.firebasetodo.model

data class Task(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val description: String = "",
    val priority: String = "",
    val dueDate: String = "",
    val dueTime: String = "",
    val url: String = "",
    val completed: Boolean = false,
    val flag: Boolean = false,
)