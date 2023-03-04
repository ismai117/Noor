package com.example.noor.android

import android.app.Application
import com.example.noor.android.authentication.authModule
import com.example.noor.android.authentication.forgetPassword.di.forgetPasswordModule
import com.example.noor.android.authentication.login.di.loginModule
import com.example.noor.android.authentication.register.di.registerModule
import com.example.noor.android.authentication.utils.validationModule
import com.example.noor.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class NoorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@NoorApplication)
            modules(
                authModule,
                loginModule,
                registerModule,
                forgetPasswordModule,
                validationModule,
            )
        }
    }

}