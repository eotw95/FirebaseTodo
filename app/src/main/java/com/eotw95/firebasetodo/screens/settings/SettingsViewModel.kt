package com.eotw95.firebasetodo.screens.settings

import com.eotw95.firebasetodo.LOGIN_SCREEN
import com.eotw95.firebasetodo.SIGN_UP_SCREEN
import com.eotw95.firebasetodo.SPLASH_SCREEN
import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val accountService: AccountService
): FirebaseTodoViewModel() {
    val uiState = accountService.currentUser.map { user ->
        SettingsUiState(isAnonymous = user.isAnonymous)
    }
    fun onSignInClick(openScreen: (String) -> Unit) = openScreen(LOGIN_SCREEN)
    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)
    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }
    fun onDeleteAccountClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.deleteAccount()
            restartApp(SPLASH_SCREEN)
        }
    }
}