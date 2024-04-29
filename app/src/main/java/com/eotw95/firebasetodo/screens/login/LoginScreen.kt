package com.eotw95.firebasetodo.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.eotw95.firebasetodo.R
import com.eotw95.firebasetodo.common.composable.BasicButton
import com.eotw95.firebasetodo.common.composable.EmailField
import com.eotw95.firebasetodo.common.composable.NoBorderButton
import com.eotw95.firebasetodo.common.composable.PasswordField
import com.eotw95.firebasetodo.common.ext.basicButton
import com.eotw95.firebasetodo.common.ext.basicColumn
import com.eotw95.firebasetodo.common.ext.fieldModifier

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    LoginScreenContent(
        uiState = uiState,
        onEmailChanged = viewModel::onEmailChange,
        onPasswordChanged = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick(openAndPopUp) },
        onForgotPasswordClick = viewModel::onForgotPasswordClick
    )
}
@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    TopAppBar(title = { Text(text = stringResource(id = R.string.sign_in)) })

    Column(
        modifier = Modifier.basicColumn(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmailField(uiState.email, onEmailChanged, Modifier.fieldModifier())
        PasswordField(uiState.password, onPasswordChanged, Modifier.fieldModifier())
        BasicButton(R.string.sign_in, onSignInClick, Modifier.basicButton())
        NoBorderButton(R.string.forgot_password, onForgotPasswordClick, Modifier.basicButton())
    }
}