package com.example.noor.android.authentication.login.di

import com.example.noor.android.authentication.login.datastore.LoginDatastore
import com.example.noor.android.authentication.login.domain.LoginRepository
import com.example.noor.android.authentication.login.domain.LoginRepositoryImpl
import com.example.noor.android.authentication.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single { LoginDatastore(get()) }
    single<LoginRepository>{ LoginRepositoryImpl(get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
}