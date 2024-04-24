package com.eotw95.firebasetodo.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.eotw95.firebasetodo.R
import com.eotw95.firebasetodo.SETTINGS_SCREEN
import com.eotw95.firebasetodo.SIGN_UP_SCREEN
import com.eotw95.firebasetodo.common.ext.isValidEmail
import com.eotw95.firebasetodo.common.ext.isValidPassword
import com.eotw95.firebasetodo.common.ext.passwordMatches
import com.eotw95.firebasetodo.common.snackbar.SnackbarManager
import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
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

    // Todo: signInすると、元のAnonymousアカウントが残っちゃうから削除するとかの対応が必要
    //  signOut(anonymousアカウント作成) -> signInを繰り返すとanonymousアカウントが増殖する
    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }
        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(R.string.password_error)
            return
        }
        if (password.passwordMatches(rePassword)) {
            SnackbarManager.showMessage(R.string.password_match_error)
            return
        }
        launchCatching {
            accountService.linkAccount(email, password)
            // Todo: signUpScreenをpopUpすれば、1つ前のバックスタックにsettingsScreenあるから、openしなくても良さそうな気もするので確認
            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
        }
    }
}