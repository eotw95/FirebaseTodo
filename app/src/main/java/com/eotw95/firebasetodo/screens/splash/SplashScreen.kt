package com.eotw95.firebasetodo.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.eotw95.firebasetodo.R
import com.eotw95.firebasetodo.common.BasicButton
import com.eotw95.firebasetodo.common.ext.basicButton
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    SplashScreenContent(
        onAppStart = { viewModel.onAppStart(openAndPopUp) },
        showError = viewModel.showError.value
    )
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    onAppStart: () -> Unit,
    showError: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showError) {
            Text(text = stringResource(id = R.string.generic_error))
            BasicButton(
                text = R.string.try_again,
                action = onAppStart,
                modifier = Modifier.basicButton()
            )
        } else {
            CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
        }
        // LaunchedEffectは親Composableの最初の描画時のみ起動し、再Composeでは動かない
        LaunchedEffect(key1 = true) {
            delay(1000L)
            onAppStart()
        }
    }
}