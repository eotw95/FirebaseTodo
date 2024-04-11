package com.eotw95.firebasetodo.screens.sign_up

import androidx.compose.runtime.mutableStateOf
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

    fun onEmailChange() {}
    fun onPasswordChange() {}
    fun onRePasswordChange() {}
    fun onSignUpClick() {}
}