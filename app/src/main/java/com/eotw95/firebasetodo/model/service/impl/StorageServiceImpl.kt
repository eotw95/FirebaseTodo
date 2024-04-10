package com.eotw95.firebasetodo.model.service.impl

import com.eotw95.firebasetodo.model.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): StorageService {
    override suspend fun authenticate(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createAnonymousAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun linkAccount(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun sendRecoveryEmail(email: String) {
        TODO("Not yet implemented")
    }
}