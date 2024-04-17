package com.eotw95.firebasetodo.common.ext

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.basicButton(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp)
}
fun Modifier.toolBarActions(): Modifier {
    return this.wrapContentSize(Alignment.TopEnd)
}
fun Modifier.smallSpacer(): Modifier {
    return this.fillMaxWidth().height(8.dp)
}
fun Modifier.fieldModifier(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 4.dp)
}
@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.basicColumn(): Modifier = Modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState())

fun Modifier.card(): Modifier {
    return this.padding(16.dp, 0.dp, 16.dp, 8.dp)
}