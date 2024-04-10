package com.eotw95.firebasetodo.model.service.impl

import com.eotw95.firebasetodo.model.Task
import com.eotw95.firebasetodo.model.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): StorageService {
    override suspend fun getAll(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String): Task? {
        TODO("Not yet implemented")
    }

    override suspend fun save(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun update(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(taskId: String) {
        TODO("Not yet implemented")
    }
}