package com.example.noor

import android.util.Log
import com.example.noor.authentication.domain.AuthRepository
import com.example.noor.authentication.utils.AuthServiceResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthRegistrar
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class AuthService(
    private val auth: FirebaseAuth,
) : AuthRepository {

    override suspend fun signIn(
        email: String,
        password: String,
    ): Flow<AuthServiceResult<Unit>> = callbackFlow {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("authService", "login: success")
                        trySend(AuthServiceResult.Success())
                    } else {
                        Log.d("authService", "login: failed")
                        trySend(AuthServiceResult.Failure(task.exception?.message))
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        awaitClose {
            cancel()
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
    ): Flow<AuthServiceResult<Unit>> = callbackFlow {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("authService", "register: success")
                        trySend(AuthServiceResult.Success())
                    } else {
                        Log.d("authService", "register: failed")
                        trySend(AuthServiceResult.Failure())
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        awaitClose {
            cancel()
        }
    }

    override suspend fun forgetPassword(
        email: String,
    ): Flow<AuthServiceResult<Unit>> = callbackFlow {
        try {
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("authService", "forgetPassword: success")
                        trySend(AuthServiceResult.Success())
                    } else {
                        Log.d("authService", "forgetPassword: ${task.exception?.message}")
                        trySend(AuthServiceResult.Failure())
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        awaitClose {
            cancel()
        }
    }

}