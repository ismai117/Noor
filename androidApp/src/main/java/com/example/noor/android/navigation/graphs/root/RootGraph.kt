package com.example.noor.android.navigation.graphs.root


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noor.android.main.MainScreen
import com.example.noor.android.navigation.graphs.auth.authNavigation
import com.example.noor.android.navigation.graphs.root.NavGraphs.AUTH
import com.example.noor.android.navigation.graphs.root.NavGraphs.MAIN
import com.example.noor.android.navigation.screens.root.RootScreens
import com.example.noor.android.splash.SplashScreen


object NavGraphs {
    const val ROOT = "root"
    const val AUTH = "auth"
    const val MAIN = "main"
}


@Composable
fun RootNavigation(
    navController: NavController,
) {
    NavHost(
        navController = navController as NavHostController,
        route = NavGraphs.ROOT,
        startDestination = RootScreens.SplashScreen.route,
    ) {
        composable(route = RootScreens.SplashScreen.route) {
            SplashScreen(
                home = {
                    navController.popBackStack()
                    navController.navigate(MAIN)
                },
                login = {
                    navController.popBackStack()
                    navController.navigate(AUTH)
                }
            )
        }
        authNavigation(
            signIn = {
                navController.popBackStack()
                navController.navigate(MAIN)
            },
            navController = navController
        )
        composable(route = MAIN) {
            MainScreen(
                signOut = {
                    navController.popBackStack()
                    navController.navigate(AUTH)
                }
            )
        }
    }
}