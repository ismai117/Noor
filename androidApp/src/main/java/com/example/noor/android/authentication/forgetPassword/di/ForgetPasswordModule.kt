package com.example.noor.android.authentication.forgetPassword.di

import com.example.noor.android.authentication.forgetPassword.domain.ForgetPasswordRepository
import com.example.noor.android.authentication.forgetPassword.domain.ForgetPasswordRepositoryImpl
import com.example.noor.android.authentication.forgetPassword.presentation.ForgetPasswordViewModel
import com.example.noor.android.authentication.login.datastore.LoginDatastore
import com.example.noor.android.authentication.login.domain.LoginRepository
import com.example.noor.android.authentication.login.domain.LoginRepositoryImpl
import com.example.noor.android.authentication.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val forgetPasswordModule = module {
    single<ForgetPasswordRepository>{ ForgetPasswordRepositoryImpl() }
    viewModel { ForgetPasswordViewModel(get(), get(), get()) }
}