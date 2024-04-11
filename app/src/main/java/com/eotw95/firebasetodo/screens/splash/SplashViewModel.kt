package com.eotw95.firebasetodo.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.screens.FirebaseTodoViewModel
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Architecture
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
): FirebaseTodoViewModel() {
    val showError = mutableStateOf(false)
    fun onAppStart() {}
    private fun createAnonymousAccount() {}
}