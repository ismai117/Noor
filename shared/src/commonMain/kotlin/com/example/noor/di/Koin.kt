package com.example.noor.di


import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin{
        appDeclaration()
    }

fun initKoin() = initKoin {}