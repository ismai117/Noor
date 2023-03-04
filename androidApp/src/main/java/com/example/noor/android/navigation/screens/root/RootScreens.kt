package com.example.noor.android.navigation.screens.root



sealed class RootScreens(val route: String) {
    object SplashScreen : RootScreens("splash_screen")
}