package com.eotw95.firebasetodo.screens.settings

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
    fun onSignInClick() {}
    fun onSignUpClick() {}
    fun onSignOutClick() {}
    fun onDeleteAccountClick() {}
}