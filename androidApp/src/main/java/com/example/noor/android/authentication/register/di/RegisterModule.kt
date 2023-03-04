package com.example.noor.android.authentication.register.di

import com.example.noor.android.authentication.login.domain.LoginRepositoryImpl
import com.example.noor.android.authentication.register.domain.RegisterRepository
import com.example.noor.android.authentication.register.domain.RegisterRepositoryImpl
import com.example.noor.android.authentication.register.presentation.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val registerModule = module {
    single<RegisterRepository>{ RegisterRepositoryImpl() }
    viewModel { RegisterViewModel(get(), get(), get(), get(), get(), get()) }
}