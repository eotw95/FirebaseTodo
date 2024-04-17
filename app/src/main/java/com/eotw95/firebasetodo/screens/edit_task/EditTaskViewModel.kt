package com.eotw95.firebasetodo.screens.edit_task

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.eotw95.firebasetodo.common.ext.idFromParameter
import com.eotw95.firebasetodo.model.Task
import com.eotw95.firebasetodo.model.service.StorageService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val storageService: StorageService
): FirebaseTodoViewModel() {
    companion object {
        private const val TASK_ID = "taskId"
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MM yyyy"
    }

    val task = mutableStateOf(Task())

    init {
        // Todo: taskIdをsavedStateHandle.getで復元しているけど、setしている実装が見当たらないのでどのtaskIdを保存しているのか謎
        val taskId = savedStateHandle.get<String>(TASK_ID)
        if (taskId != null) {
            launchCatching {
                task.value = storageService.getTask(taskId.idFromParameter()) ?: Task()
            }
        }
    }

    fun onTitleChange(newValue: String) {
        task.value = task.value.copy(title = newValue)
    }
    fun onDescriptionChange(newValue: String) {
        task.value = task.value.copy(description = newValue)
    }
    fun onUrlChange(newValue: String) {
        task.value = task.value.copy(url = newValue)
    }
    fun onDateChange(newValue: Long) {
        // Todo: Calender使ってやる以外の方法もあるはずなので、後で確認
        val calender = Calendar.getInstance(TimeZone.getTimeZone(UTC))
        calender.timeInMillis = newValue
        val newDate = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(calender.time)
        task.value = task.value.copy(dueDate = newDate)
    }
    fun onTimeChange(hour: Int, minute: Int) {
        val newValue = "${hour.toClockPattern()}:${minute.toClockPattern()}"
        task.value = task.value.copy(dueTime = newValue)
    }
    fun onPriorityChange(newValue: String) {
        task.value = task.value.copy(priority = newValue)
    }
    fun onFlagToggle(newValue: String) {
        val flag = EditFlagOption.getBoolean(newValue)
        task.value = task.value.copy(flag = flag)
    }
    fun onDoneClicked(popupScreen: () -> Unit) {
        launchCatching {
            if (task.value.id.isBlank())
                storageService.insert(task.value)
            else
                storageService.update(task.value)
        }
        popupScreen()
    }
    private fun Int.toClockPattern(): String  = if (this < 10) "0$this" else this.toString()
}