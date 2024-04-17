package com.eotw95.firebasetodo.screens.tasks

import com.eotw95.firebasetodo.EDIT_TASK_SCREEN
import com.eotw95.firebasetodo.model.Task
import com.eotw95.firebasetodo.model.service.StorageService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val storageService: StorageService
): FirebaseTodoViewModel() {
    val uiState = storageService.tasks
    val taskActionOptions = TaskActionOptions.options

    fun onSettingsClick(openScreen: (String) -> Unit) {
        openScreen("settingsScreen")
    }
    fun onTaskCheckChangeClick(task: Task) {
        launchCatching { storageService.update(task.copy(completed = !task.completed)) }
    }
    fun onAddClick(openScreen: (String) -> Unit) {
        openScreen(EDIT_TASK_SCREEN)
    }
    fun onTaskActionClick(openScreen: (String) -> Unit, task: Task, action: String) {
        when (action) {
            TaskActionOptions.EditTask.name -> onEditTaskClick(openScreen, task)
            TaskActionOptions.ToggleFlag.name -> onToggleFlagClick(task)
            TaskActionOptions.DeleteTask.name -> onDeleteTaskClick(task)
        }
    }
    private fun onEditTaskClick(openScreen: (String) -> Unit, task: Task) {
        openScreen("$EDIT_TASK_SCREEN?taskId={${task.id}}")
    }
    private fun onToggleFlagClick(task: Task) {
        launchCatching { storageService.update(task.copy(flag = !task.flag)) }
    }
    private fun onDeleteTaskClick(task: Task) {
        launchCatching { storageService.delete(task.id) }
    }
}