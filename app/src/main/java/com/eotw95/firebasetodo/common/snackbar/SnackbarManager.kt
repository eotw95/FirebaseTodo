package com.eotw95.firebasetodo.common.snackbar

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SnackbarManager {
    private val _messages: MutableStateFlow<SnackbarMessage?> = MutableStateFlow(null)
    val messages: StateFlow<SnackbarMessage?>
        get() = _messages.asStateFlow()
    fun showMessage(message: SnackbarMessage) { _messages.value =  message }
    fun showMessage(@StringRes message: Int) {
        _messages.value = SnackbarMessage.ResourceSnackMessage(message)
    }
    fun clearSnackbarState() { _messages.value = null }
}