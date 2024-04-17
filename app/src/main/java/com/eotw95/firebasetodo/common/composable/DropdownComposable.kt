package com.eotw95.firebasetodo.common.composable

import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownContextMenu( // Todo: 関数名が微妙な気がする
    options: List<String>,
    modifier: Modifier,
    onActionClick: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.width(180.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onActionClick(option)
                    }
                ) {
                    Text(text = option)
                }
            }
        }
    }
}