package com.eotw95.firebasetodo.model.service.impl

import com.eotw95.firebasetodo.model.Task
import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.model.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val account: AccountService
): StorageService {
    companion object {
        private const val TASK_COLLECTION = "tasks"
        private const val USER_ID_FIELD = "userId"
    }
    override val tasks: Flow<List<Task>>
        get() = account.currentUser.flatMapLatest { user ->
            firestore.collection(TASK_COLLECTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
        }

    override suspend fun getTask(taskId: String): Task? =
        firestore.collection(TASK_COLLECTION).document(taskId).get().await().toObject<Task>()

    override suspend fun insert(task: Task) {
        val taskWithUserId = task.copy(userId = account.currentUserId)
        firestore.collection(TASK_COLLECTION).add(taskWithUserId).await()
    }

    override suspend fun update(task: Task) {
        firestore.collection(TASK_COLLECTION).document(task.id).set(task)
    }

    override suspend fun delete(taskId: String) {
        firestore.collection(TASK_COLLECTION).document(taskId).delete().await()
    }
}