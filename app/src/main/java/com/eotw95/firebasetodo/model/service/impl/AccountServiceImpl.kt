package com.eotw95.firebasetodo.model.service.impl

import com.eotw95.firebasetodo.model.User
import com.eotw95.firebasetodo.model.service.AccountService
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth): AccountService  {
    override val currentUser: Flow<User>
        // Todo: callbackFlow{}内の処理が理解できていないので後で確認する
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let {
                    User(id = it.uid, isAnonymous = it.isAnonymous)
                } ?: User())
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override suspend fun authenticate(email: String, password: String) {
        // await()はsignInWithEmailAndPassword()の戻り値を取得するが、authenticate()は戻り値ないので必要ない
        // authenticate()の戻り値(AuthResult)を定義して、呼び出し元で評価する場合は必要
        // 今後、AuthResultを使うかもしれないので、一旦実装しておく
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    override suspend fun linkAccount(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        auth.currentUser!!.linkWithCredential(credential).await()
    }

    override suspend fun signOut() {
        // Todo: そもそもAnonymousでSignInはできない気がするので、delete()は必要ないかも
        if (auth.currentUser!!.isAnonymous) {
            auth.currentUser!!.delete()
        }
        auth.signOut()

        // signOut後はanonymous accountに切り替える
        createAnonymousAccount()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }
}