package com.example.noor.android.main.discover


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsAccessibility
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.noor.android.R
import com.example.noor.android.main.components.TopBar
import com.example.noor.android.navigation.screens.main.MainScreens


@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                modifier = modifier,
                leadingIcon =null,
                title = "",
                trailingIcon = Icons.Filled.Settings,
                leadingIconClicked = {},
                trailingIconClicked = {
                    navController.navigate(MainScreens.SettingsScreen.route)
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF000000))
        ) {
            Image(
                painter = painterResource(id = R.drawable.texture_bg),
                contentDescription = "",
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFC0C0C0).copy(0.20f)),
                contentScale = ContentScale.Crop,
                alpha = 0.80f
            )
            Text(text = "Discover", color = Color.White)
        }
    }

}