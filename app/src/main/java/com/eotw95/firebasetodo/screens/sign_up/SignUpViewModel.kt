package com.eotw95.firebasetodo.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.eotw95.firebasetodo.common.ext.isValidEmail
import com.eotw95.firebasetodo.common.ext.isValidPassword
import com.eotw95.firebasetodo.common.ext.passwordMatches
import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
): FirebaseTodoViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password
    private val rePassword
        get() = uiState.value.rePassword

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }
    fun onRePasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(rePassword = newValue)
    }
    fun onSignUpClick(openAndPopUpScreen: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            // Todo: snackbar表示
            return
        }
        if (!password.isValidPassword() || password.passwordMatches(rePassword)) {
            // Todo: snackbar表示
            return
        }
        launchCatching {
            accountService.linkAccount(email, password)
            // Todo: signUpScreenをpopUpすれば、1つ前のバックスタックにsettingsScreenあるから、openしなくても良さそうな気もするので確認
            openAndPopUpScreen("settingScreen", "signUpScreen")
        }
    }
}