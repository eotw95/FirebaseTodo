package com.eotw95.firebasetodo.common.composable

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun BasicButton(text: Int, action: () -> Unit, modifier: Modifier) {
    Button(
        modifier = modifier,
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(id = text), fontSize = 16.sp)
    }
}
@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(id = text))
    }
}
@Composable
fun DialogCancelButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colors.onPrimary,
            backgroundColor = MaterialTheme.colors.primary
        )
    ) {
        Text(text = stringResource(id = text))
    }
}