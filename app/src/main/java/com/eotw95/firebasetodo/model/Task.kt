package com.eotw95.firebasetodo.model

import com.google.firebase.firestore.DocumentId

data class Task(
    @DocumentId val id: String = "",
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