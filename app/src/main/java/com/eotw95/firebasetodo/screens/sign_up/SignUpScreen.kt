package com.eotw95.firebasetodo.screens.sign_up

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
import com.eotw95.firebasetodo.common.composable.PasswordField
import com.eotw95.firebasetodo.common.composable.RePasswordField
import com.eotw95.firebasetodo.common.composable.toolBarColor
import com.eotw95.firebasetodo.common.ext.basicButton
import com.eotw95.firebasetodo.common.ext.basicColumn
import com.eotw95.firebasetodo.common.ext.fieldModifier

@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    SignUpScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRePasswordChange = viewModel::onRePasswordChange,
        onSignUpClick = { viewModel.onSignUpClick(openAndPopUp) }
    )
}
@Composable
fun SignUpScreenContent(
    uiState: SignUpUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRePasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.create_account)) },
        backgroundColor = toolBarColor()
    )
    Column(
        modifier = Modifier.basicColumn(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val fieldModifier = Modifier.fieldModifier()
        EmailField(uiState.email, onEmailChange, fieldModifier)
        PasswordField(uiState.password, onPasswordChange, fieldModifier)
        RePasswordField(uiState.rePassword, onRePasswordChange, fieldModifier)

        BasicButton(R.string.create_account, onSignUpClick, Modifier.basicButton())
    }
}