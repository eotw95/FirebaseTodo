package com.eotw95.firebasetodo.screens.edit_task

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.eotw95.firebasetodo.common.ext.idFromParameter
import com.eotw95.firebasetodo.model.Task
import com.eotw95.firebasetodo.model.service.StorageService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val storageService: StorageService
): FirebaseTodoViewModel() {
    companion object {
        private const val TASK_ID = "taskId"
    }

    private val task = mutableStateOf(Task())

    init {
        val taskId = savedStateHandle.get<String>(TASK_ID)
        if (taskId != null) {
            launchCatching {
                task.value = storageService.getTask(taskId.idFromParameter()) ?: Task()
            }
        }
    }

    fun onTitleChange() {}
    fun onDescriptionChange() {}
    fun onUrlChange() {}
    fun onDateChange() {}
    fun onTimeChange() {}
    fun onPriorityChange() {}
    fun onFlagToggle() {}
    fun onDoneClicked() {}
}