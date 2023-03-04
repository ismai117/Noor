package com.example.noor.android.authentication.login.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LoginDatastore(
    private val context: Context
) {

    fun getLoginState(): Flow<Boolean> {
        return context.datastore.data
            .map { preferences ->
                preferences[STATE] ?: false
            }
    }

    suspend fun setLoginState(state: Boolean) {
        context.datastore.edit { preferences ->
            preferences[STATE] = state
        }
    }

    companion object {
        val Context.datastore: DataStore<Preferences> by preferencesDataStore("loginState")
        val STATE = booleanPreferencesKey("state")
    }

}