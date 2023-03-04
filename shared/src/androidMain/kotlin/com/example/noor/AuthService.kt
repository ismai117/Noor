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
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun signUp(
        email: String,
        password: String,
    ): Flow<AuthServiceResult<Unit>> = callbackFlow {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("authService", "${task.result.user?.uid}")
                        trySend(AuthServiceResult.Success(task.result.user?.uid))
                    } else {
                        Log.d("authService", "${task.exception?.message}")
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

}