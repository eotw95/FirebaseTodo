package com.eotw95.firebasetodo.common.composable

import androidx.annotation.StringRes
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun BasicTextField(
    @StringRes text: Int,
    value: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        singleLine = true,
        value = value,
        modifier = modifier,
        onValueChange = { newValue -> onValueChanged(newValue) },
        placeholder = { Text(text = stringResource(id = text)) }
    )
}