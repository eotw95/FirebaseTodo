package com.eotw95.firebasetodo.screens.tasks

import com.eotw95.firebasetodo.model.service.StorageService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val storageService: StorageService
): FirebaseTodoViewModel() {
    val uiState = storageService.tasks

    fun onSettingsClick() {}
    fun onTaskCheckChangeClick() {}
    fun onAddClick() {}
    fun onTaskActionClick() {}
    private fun onEditTaskClick() {}
    private fun onToggleFlagClick() {}
    private fun onDeleteTaskClick() {}
}