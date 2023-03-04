package com.example.noor.android.authentication.utils



class ValidateRepeatedPassword  {

    fun execute(password: String, repeatedPassword: String): ValidateResult {
        if(password != repeatedPassword){
            return ValidateResult(
                successful = false,
                errorMessage = "This password doesn't match"
            )
        }
        return ValidateResult(
            successful = true
        )
    }

}