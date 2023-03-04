package com.example.noor.android.authentication.utils

import org.koin.dsl.module


val validationModule = module {
    single { ValidateUsername() }
    single { ValidateEmail() }
    single { ValidatePassword() }
    single { ValidateRepeatedPassword() }
}