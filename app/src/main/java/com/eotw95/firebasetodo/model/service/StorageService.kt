package com.eotw95.firebasetodo.model.service

import com.eotw95.firebasetodo.model.Task

interface StorageService {
    suspend fun getAll(): List<Task>
    suspend fun getTask(taskId: String): Task?
    suspend fun save(task: Task)
    suspend fun update(task: Task)
    suspend fun delete(taskId: String)
}