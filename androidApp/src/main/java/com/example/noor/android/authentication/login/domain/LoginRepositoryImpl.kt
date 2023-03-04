package com.example.noor.android.authentication.login.domain

import com.example.noor.android.authentication.login.datastore.LoginDatastore
import kotlinx.coroutines.flow.Flow


class LoginRepositoryImpl(
    private val loginDatastore: LoginDatastore
) : LoginRepository {

    override fun getLoginState(): Flow<Boolean> {
        return loginDatastore.getLoginState()
    }

    override suspend fun setLoginState(state: Boolean) {
        loginDatastore.setLoginState(state)
    }

}