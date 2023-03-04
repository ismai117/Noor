package com.example.noor.android.navigation.graphs.auth



import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.noor.android.authentication.forgetPassword.presentation.ForgotPasswordScreen
import com.example.noor.android.authentication.login.presentation.LogdinScreen
import com.example.noor.android.authentication.register.presentation.RegisterScreen
import com.example.noor.android.authentication.starter.StarterScreen
import com.example.noor.android.navigation.graphs.root.NavGraphs
import com.example.noor.android.navigation.screens.auth.AuthScreens

fun NavGraphBuilder.authNavigation(
    signIn: () -> Unit,
    navController: NavController
) {
    navigation(
        route = NavGraphs.AUTH,
        startDestination = AuthScreens.StarterScreen.route
    ) {
        composable(route = AuthScreens.StarterScreen.route) {
            StarterScreen(
                navController = navController
            )
        }
        composable(route = AuthScreens.LoginScreen.route) {
            LogdinScreen(
                navController = navController,
                signIn = {
                    signIn()
                }
            )
        }
        composable(route = AuthScreens.RegisterScreen.route) {
            RegisterScreen(
                navController = navController
            )
        }
        composable(route = AuthScreens.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(
                navController = navController
            )
        }
    }
}