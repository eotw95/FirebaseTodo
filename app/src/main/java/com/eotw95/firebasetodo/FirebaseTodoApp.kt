package com.eotw95.firebasetodo

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eotw95.firebasetodo.common.snackbar.SnackbarManager
import com.eotw95.firebasetodo.screens.edit_task.EditTasksScreen
import com.eotw95.firebasetodo.screens.login.LoginScreen
import com.eotw95.firebasetodo.screens.settings.SettingsScreen
import com.eotw95.firebasetodo.screens.sign_up.SignUpScreen
import com.eotw95.firebasetodo.screens.splash.SplashScreen
import com.eotw95.firebasetodo.screens.tasks.TasksScreen
import com.eotw95.firebasetodo.ui.theme.FirebaseTodoTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun FirebaseTodoApp() {
    FirebaseTodoTheme {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()
            Scaffold(
                scaffoldState = appState.scaffoldState
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navHostController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    firebaseTodoAppGraph(appState)
                }
            }
        }
    }
}

/**
 *  FirebaseTodoAppStateをrememberで保持
 */
@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navHostController: NavHostController = rememberNavController(),
    snackbarManage: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, navHostController, snackbarManage, resources, coroutineScope) {
        FirebaseTodoAppState(
            scaffoldState, navHostController, snackbarManage, resources, coroutineScope
        )
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current // Todo: このステップの意図がわからん　削除漏れ？
    return LocalContext.current.resources
}

fun NavGraphBuilder.firebaseTodoAppGraph(appState: FirebaseTodoAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            openScreen = { route -> appState.navigate(route) },
            restartApp = { route -> appState.clearAndNavigate(route) }
        )
    }
    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(TASKS_SCREEN) {
        TasksScreen(openScreen = { route -> appState.navigate(route) })
    }
    composable(
        route = EDIT_TASK_SCREEN + TASK_ID_ARG,
        arguments = listOf(navArgument(TASK_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        EditTasksScreen(popupScreen = { appState.popUp() })
    }
}