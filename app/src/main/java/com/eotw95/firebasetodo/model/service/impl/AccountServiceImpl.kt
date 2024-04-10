package com.eotw95.firebasetodo.model.service.impl

import com.eotw95.firebasetodo.model.Task
import com.eotw95.firebasetodo.model.service.AccountService

class AccountServiceImpl(): AccountService  {
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