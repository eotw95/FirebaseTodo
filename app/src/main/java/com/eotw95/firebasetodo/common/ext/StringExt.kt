package com.eotw95.firebasetodo.common.ext

// Todo: substring(1, this.length -1)の実装の意図がよくわからん
fun String.idFromParameter(): String = this.substring(1, this.length -1)