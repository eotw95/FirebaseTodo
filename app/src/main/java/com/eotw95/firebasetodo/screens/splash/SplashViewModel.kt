package com.eotw95.firebasetodo.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
): FirebaseTodoViewModel() {
    val showError = mutableStateOf(false)
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
        if (accountService.hasUser) openAndPopUp("tasksScreen", "splashScreen")
        else createAnonymousAccount(openAndPopUp)
    }
    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            // Todo: Exception発生するルートに入った時に、後続のopenAndPopUp()は呼ばれていいのか？
            openAndPopUp("tasksScreen", "splashScreen")
        }
    }
}