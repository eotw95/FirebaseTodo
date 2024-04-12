package com.eotw95.firebasetodo.common.ext

import android.util.Patterns

// Todo: substring(1, this.length -1)の実装の意図がよくわからん
fun String.idFromParameter(): String = this.substring(1, this.length -1)
fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}