package com.eotw95.firebasetodo.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistantPhoto
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eotw95.firebasetodo.common.composable.DropdownContextMenu
import com.eotw95.firebasetodo.model.Task

@Composable
fun TaskItem(
    task: Task,
    options: List<String>,
    onCheckChange: () -> Unit,
    onActionClick: (String) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onCheckChange() },
                modifier = Modifier.padding(8.dp, 0.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, style = MaterialTheme.typography.subtitle2)
                // Todo: 引数の文法がよくわからん
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = formatDateAndTime(task), fontSize = 12.sp)
                }
            }
            if (task.flag) {
                Icon(imageVector = Icons.Filled.AssistantPhoto, contentDescription = "Flag")
            }
            DropdownContextMenu(
                options = options,
                modifier = Modifier.wrapContentWidth(),
                onActionClick = onActionClick
            )
        }
    }
}

private fun formatDateAndTime(task: Task): String {
    val strBuilder = StringBuilder("")
    if (task.dueDate.isNotBlank()) {
        strBuilder.append(task.dueDate)
        strBuilder.append(" ")
    }
    if (task.dueTime.isNotBlank()) {
        strBuilder.append("at ")
        strBuilder.append(task.dueTime)
    }
    return strBuilder.toString()
}