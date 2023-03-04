package com.example.noor.android.authentication

import com.example.noor.AuthService
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


val authModule = module {
    single { FirebaseAuth.getInstance() }
    single { AuthService(get())}
}