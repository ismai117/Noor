package com.example.noor.android.authentication.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noor.AuthService
import com.example.noor.android.authentication.login.domain.LoginRepository
import com.example.noor.android.authentication.utils.ValidateEmail
import com.example.noor.android.authentication.utils.ValidatePassword
import com.example.noor.android.authentication.utils.ValidationResult
import com.example.noor.authentication.utils.AuthServiceResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val authRepository: AuthService,
    private val loginRepository: LoginRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    var state by mutableStateOf(LoginState())

    private val _loginState = MutableStateFlow<AuthServiceResult<Unit>?>(null)
    val loginState =_loginState .asStateFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is LoginEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {

        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any{ !it.successful }

        if(hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            authRepository.signIn(
                state.email,
                state.password
            ).collect {
                state = state.copy(isLoading = true)
                delay(2000)
                _loginState.value = it
                state = state.copy(isLoading = false)
            }
        }
    }

}