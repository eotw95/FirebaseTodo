package com.eotw95.firebasetodo.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun ActionToolBar(
    @StringRes title: Int,
    endActionIcon: ImageVector,
    modifier: Modifier,
    endAction: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(id = title)) },
        backgroundColor = toolBarColor(),
        actions = {
            Box(modifier = modifier) {
                IconButton(onClick = endAction) {
                    Icon(imageVector = endActionIcon, contentDescription = "Action")
                }
            }
        }
    )
}

@Composable
fun toolBarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (darkTheme) MaterialTheme.colors.secondary else MaterialTheme.colors.primaryVariant
}