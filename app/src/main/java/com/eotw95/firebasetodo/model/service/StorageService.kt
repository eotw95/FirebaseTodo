package com.eotw95.firebasetodo.model.service

import com.eotw95.firebasetodo.model.Task
import com.eotw95.firebasetodo.model.User
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val tasks: Flow<List<Task>>
    suspend fun getTask(taskId: String): Task?
    suspend fun insert(task: Task)
    suspend fun update(task: Task)
    suspend fun delete(taskId: String)
}