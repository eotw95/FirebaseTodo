package com.eotw95.firebasetodo.common.ext

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

// Todo: substring(1, this.length -1)の実装の意図がよくわからん
fun String.idFromParameter(): String = this.substring(1, this.length -1)
fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
           this.length >= MIN_PASS_LENGTH &&
           Pattern.compile(PASS_PATTERN).matcher(this).matches()
}
fun String.passwordMatches(input: String): Boolean = this == input