package com.eotw95.firebasetodo.common.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes
import com.eotw95.firebasetodo.R

sealed class SnackbarMessage {
    class StringSnackMessage(val message: String): SnackbarMessage()
    class ResourceSnackMessage(@StringRes val message: Int): SnackbarMessage()

    fun SnackbarMessage.toMessage(resource: Resources): String {
        return when (this) {
            is StringSnackMessage -> this.message
            is ResourceSnackMessage -> resource.getString(this.message)
        }
    }
    fun Throwable.toSnackbarMessage(): SnackbarMessage {
        val message = this.message.orEmpty()
        return if (message.isBlank()) StringSnackMessage(message)
        else ResourceSnackMessage(R.string.generic_error)
    }
}