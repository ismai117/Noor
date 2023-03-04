package com.example.noor.android.authentication.login.presentation


data class LoginState(
    val email: String = "",
    val emailError: String? = "",
    val password: String = "",
    val passwordError: String? = ""
)