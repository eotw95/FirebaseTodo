package com.eotw95.firebasetodo.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eotw95.firebasetodo.common.snackbar.SnackbarManager
import com.eotw95.firebasetodo.common.snackbar.SnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class FirebaseTodoViewModel: ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(
            context = CoroutineExceptionHandler { _, throwable ->
                // Todo: snackbarの表示
                if (snackbar) {
                    SnackbarManager.showMessage(
                        SnackbarMessage.StringSnackMessage(throwable.toString())
                    )
                }
            },
            block = block
        )
    }
}