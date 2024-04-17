package com.eotw95.firebasetodo.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun RegularCardEditor(
    @StringRes title: Int,
    icon: ImageVector,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit
) {
    CardEditor(
        title,
        icon,
        content,
        modifier,
        onEditClick,
        MaterialTheme.colors.onSurface
    )
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CardEditor(
    @StringRes title: Int,
    icon: ImageVector,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit,
    highLightColor: Color
) {
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = modifier,
        onClick = onEditClick
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = title), color = highLightColor)
            }
            if (content.isNotBlank()) Text(text = content, modifier = Modifier.padding(16.dp, 0.dp))
            Icon(imageVector = icon, contentDescription = "Icon")
        }
    }
}