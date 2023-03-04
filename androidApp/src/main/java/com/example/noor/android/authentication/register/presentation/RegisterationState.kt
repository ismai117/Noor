package com.example.noor.android.authentication.register.presentation


data class RegistrationState(
    val username: String = "",
    val usernameError: String? = "",
    val email: String = "",
    val emailError: String? = "",
    val password: String = "",
    val passwordError: String?= "",
    val repeatPassword: String = "",
    val repeatPasswordError: String? = ""
)