package com.example.noor.android.authentication.utils



sealed class ValidationResult{
    class Successful(val uID: String? = null) : ValidationResult()
    class Unsuccessful(val message: String? = null) : ValidationResult()
}
