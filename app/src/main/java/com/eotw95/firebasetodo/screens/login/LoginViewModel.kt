package com.eotw95.firebasetodo.screens.login

import androidx.compose.runtime.mutableStateOf
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
        get() = mutableStateOf(uiState.value.email)
    private val password
        get() = mutableStateOf(uiState.value.password)

    fun onSignInClick() {}
    fun onEmailChange() {}
    fun onPasswordChange() {}
    fun onForgotPasswordClick() {}
}