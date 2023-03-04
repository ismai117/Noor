package com.example.noor.android.container

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.noor.android.navigation.graphs.root.RootNavigation


@Composable
fun ContainerScreen() {

    val navController = rememberNavController()

    RootNavigation(
        navController = navController
    )

}