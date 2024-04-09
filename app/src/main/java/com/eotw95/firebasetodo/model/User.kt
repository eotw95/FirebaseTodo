package com.eotw95.firebasetodo.model

data class User(
    val id: String = "",
    val name: String = "",
    val password: String = "",
    val isAnonymous: Boolean = true
)