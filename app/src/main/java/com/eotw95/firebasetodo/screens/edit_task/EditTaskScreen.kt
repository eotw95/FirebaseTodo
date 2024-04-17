package com.eotw95.firebasetodo.screens.edit_task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.eotw95.firebasetodo.R
import com.eotw95.firebasetodo.common.composable.ActionToolBar
import com.eotw95.firebasetodo.common.composable.BasicTextField
import com.eotw95.firebasetodo.common.composable.RegularCardEditor
import com.eotw95.firebasetodo.common.ext.basicColumn
import com.eotw95.firebasetodo.common.ext.card
import com.eotw95.firebasetodo.common.ext.fieldModifier
import com.eotw95.firebasetodo.common.ext.toolBarActions
import com.eotw95.firebasetodo.model.Task

@Composable
fun EditTasksScreen(
    popupScreen: () -> Unit,
    viewModel: EditTaskViewModel = hiltViewModel()
) {
    val task by viewModel.task

    EditTasksScreenContent(
        task = task,
        onDoneClick = { viewModel.onDoneClicked(popupScreen) },
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onUrlChange = viewModel::onUrlChange,
        onDateChange = viewModel::onDateChange,
        onTimeChange = viewModel::onTimeChange
    )
}
@Composable
private fun EditTasksScreenContent(
    task: Task,
    onDoneClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onDateChange: (Long) -> Unit,
    onTimeChange: (Int, Int) -> Unit
) {
    Column(
        modifier = Modifier.basicColumn(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolBar(
            title = R.string.edit_task,
            endActionIcon = Icons.Default.Check,
            modifier = Modifier.toolBarActions(),
            endAction = onDoneClick
        )

        val fieldModifier = Modifier.fieldModifier()
        BasicTextField(R.string.title, task.title, fieldModifier, onTitleChange)
        BasicTextField(R.string.description, task.description, fieldModifier, onDescriptionChange)
        BasicTextField(R.string.url, task.url, fieldModifier, onUrlChange)

        // Todo: Date and Time edit card
        CardEditors(task = task, onDateChange = onDateChange, onTimeChange = onTimeChange)

        // Todo: Priority and Flag edit selector
        CardSelector()
    }
}
@Composable
private fun CardEditors(
    task: Task,
    onDateChange: (Long) -> Unit,
    onTimeChange: (Int, Int) -> Unit
) {
    RegularCardEditor(
        title = R.string.date,
        icon = Icons.Default.CalendarMonth,
        content = task.dueDate,
        modifier = Modifier.card(),
        onEditClick = { showDatePicker() }
    )
}
@Composable
private fun CardSelector() {}
private fun showDatePicker() {
    // Todo: not implemented
}
private fun showTimePicker() {
    // Todo: not implemented
}