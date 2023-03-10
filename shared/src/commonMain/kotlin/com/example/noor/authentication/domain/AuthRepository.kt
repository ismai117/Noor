package com.example.noor.authentication.domain


import com.example.noor.authentication.utils.AuthServiceResult
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    suspend fun signIn(email: String, password: String): Flow<AuthServiceResult<Unit>>
    suspend fun signUp(email: String, password: String): Flow<AuthServiceResult<Unit>>
    suspend fun forgetPassword(email: String): Flow<AuthServiceResult<Unit>>
}

