package com.example.noor.android.authentication.forgetPassword.presentation


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noor.android.authentication.forgetPassword.domain.ForgetPasswordRepository
import com.example.noor.android.authentication.register.presentation.RegistrationEvent
import com.example.noor.android.authentication.utils.ValidateEmail
import com.example.noor.android.authentication.utils.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class ForgetPasswordViewModel(
    private val forgetPasswordRepository: ForgetPasswordRepository,
    private val validateEmail: ValidateEmail,
) : ViewModel() {

    var state by mutableStateOf(ForgetPasswordState())

    private val validationEventChannel = Channel<ValidationResult>()
    val validateEvents = validationEventChannel.receiveAsFlow()

    val isLoading = mutableStateOf(false)

    fun onEvent(event: ForgetPasswordEvent){
        when(event){
            is  ForgetPasswordEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is  ForgetPasswordEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {


        val emailResult = validateEmail.execute(state.email)

        if (!emailResult.successful) {
            state = state.copy(
                emailError = emailResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationResult.Successful(""))
        }

    }

}