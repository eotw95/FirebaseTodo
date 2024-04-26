package com.eotw95.firebasetodo

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.eotw95.firebasetodo.common.snackbar.SnackbarManager
import com.eotw95.firebasetodo.common.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

/**
 *  State Holder Class
 */
@Stable
class FirebaseTodoAppState(
    val scaffoldState: ScaffoldState,
    val navHostController: NavHostController,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            snackbarManager.messages.filterNotNull().collect { messages ->
                val text = messages.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(message = text)
                snackbarManager.clearSnackbarState()
            }
        }
    }

    fun popUp() = navHostController.popBackStack()
    fun navigate(route: String) = navHostController.navigate(route) { launchSingleTop = true }
    fun navigateAndPopUp(route: String, popUp: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }
    fun clearAndNavigate(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}