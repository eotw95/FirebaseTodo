package com.eotw95.firebasetodo.screens.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Android
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eotw95.firebasetodo.R
import com.eotw95.firebasetodo.common.ActionToolBar
import com.eotw95.firebasetodo.common.ext.smallSpacer
import com.eotw95.firebasetodo.common.ext.toolBarActions
import com.eotw95.firebasetodo.model.Task

@Composable
fun TasksScreen(
    openScreen: (String) -> Unit,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val tasks = viewModel.uiState.collectAsStateWithLifecycle(initialValue = emptyList())
    TasksScreenContent(
        onSettingsClick = viewModel::onSettingsClick,
        onTaskActionClick = viewModel::onTaskActionClick,
        onTaskCheckChangeClick = viewModel::onTaskCheckChangeClick,
        onAddClick = viewModel::onAddClick,
        openScreen = openScreen,
        tasks = tasks.value,
        options = viewModel.taskActionOptions
    )
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TasksScreenContent(
    modifier: Modifier = Modifier,
    onSettingsClick: ((String) -> Unit) -> Unit,
    onTaskCheckChangeClick: (Task) -> Unit,
    onAddClick: ((String) -> Unit) -> Unit,
    onTaskActionClick: ((String) -> Unit, Task, String) -> Unit,
    openScreen: (String) -> Unit,
    tasks: List<Task>,
    options: List<String>
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick(openScreen) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
        ) {
            ActionToolBar(
                title = R.string.tasks,
                endActionIcon = Icons.Default.Android,
                modifier = Modifier.toolBarActions(),
                endAction = { onSettingsClick(openScreen) }
            )
            Spacer(modifier = Modifier.smallSpacer())
            LazyColumn {
                items(
                    items = tasks,
                    key = { it.id }
                ) { task ->
                    TaskItem() // Todo: not implemented
                }
            }
        }
    }
}