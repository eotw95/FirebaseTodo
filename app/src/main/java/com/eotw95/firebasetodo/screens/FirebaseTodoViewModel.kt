package com.eotw95.firebasetodo.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class FirebaseTodoViewModel: ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(
            context = CoroutineExceptionHandler { _, throwable ->
                // Todo: snackbarの表示
                if (snackbar) println("show message $throwable")
            },
            block = block
        )
    }
}