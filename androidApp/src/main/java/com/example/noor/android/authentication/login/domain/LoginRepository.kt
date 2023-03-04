package com.example.noor.android.authentication.login.domain

import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    fun getLoginState(): Flow<Boolean>

    suspend fun setLoginState(state: Boolean)

}