package com.eotw95.firebasetodo.screens.login

import androidx.compose.runtime.mutableStateOf
import com.eotw95.firebasetodo.common.ext.isValidEmail
import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
): FirebaseTodoViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            // Todo: snackbarの実装
            return
        }
        if (password.isBlank()) {
            // Todo: snackbarの実装
            return
        }
        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp("settingsScreen","loginScreen")
        }
    }
    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)

    }
    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            // Todo: snackbarの実装
            return
        }
        launchCatching {
            accountService.sendRecoveryEmail(email)
            // Todo: snackbarの実装 check email
        }
    }
}