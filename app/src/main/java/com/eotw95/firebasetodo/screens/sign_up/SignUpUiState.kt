package com.eotw95.firebasetodo.screens.sign_up

data class SignUpUiState(
    val email:String = "",
    val password: String = "",
    val rePassword: String = "",
    val isShowPass: Boolean = false,
    val isShowRePass: Boolean = false
)
