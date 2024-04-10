package com.eotw95.firebasetodo.model.service

interface StorageService {
    suspend fun authenticate(email: String, password: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
    suspend fun sendRecoveryEmail(email: String)
}