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
import androidx.navigation.compose.rememberNavController
import com.eotw95.firebasetodo.common.snackbar.SnackbarManager
import com.eotw95.firebasetodo.ui.theme.FirebaseTodoTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun FirebaseTodoApp() {
    FirebaseTodoTheme {
        // Todo: この実装が必要なのかどうか分からないので確認
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            RequestNotificationPermissionDialog()
//        }

        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = { snackbarHostState ->
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier.padding(8.dp),
                        snackbar = {
                            Snackbar(snackbarData = it, contentColor = MaterialTheme.colors.background)
                        }
                    )
                },
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
    // Todo: implementation
}