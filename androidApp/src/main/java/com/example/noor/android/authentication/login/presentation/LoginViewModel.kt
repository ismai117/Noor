package com.example.noor.android.authentication.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noor.android.authentication.login.domain.LoginRepository
import com.example.noor.android.authentication.utils.ValidateEmail
import com.example.noor.android.authentication.utils.ValidatePassword
import com.example.noor.android.authentication.utils.ValidationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    private val _loginState = MutableStateFlow(false)
    val loginState = _loginState.asStateFlow()

    var state by mutableStateOf(LoginState())

    private val validationEventChannel = Channel<ValidationResult>()
    val validateEvents = validationEventChannel.receiveAsFlow()

    val isLoading = mutableStateOf(false)

    init {
        getLoginState()
    }

    private fun getLoginState(){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.getLoginState().collect{
                _loginState.value = it
            }
        }
    }

    fun setLoginState(state: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.setLoginState(state)
        }
    }

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
            validationEventChannel.send(ValidationResult.Successful(""))
        }

    }

}