package com.example.noor.android.main.settings


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noor.android.R
import com.example.noor.android.main.components.TopBar
import com.example.noor.android.navigation.screens.auth.AuthScreens


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signOut: () -> Unit,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                modifier = modifier,
                leadingIcon = Icons.Filled.ArrowBack,
                title = "",
                trailingIcon = null,
                leadingIconClicked = {
                    navController.popBackStack()
                },
                trailingIconClicked = {

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
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 44.dp, start = 12.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = modifier
                            .size(35.dp)
                            .background(color = Color.White, shape = CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.apple_icon),
                            contentDescription = "",
                            modifier = modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = "Ismail Mohamed",
                        color = Color.White,
                        modifier = modifier.padding(start = 12.dp)
                    )
                }
                Divider(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 12.dp, end = 12.dp),
                    thickness = 1.dp,
                    color = Color(0xFFFFD300)
                )
                Row(
                    modifier = modifier.height(50.dp)
                ) {

                }
            }
        }
    }

}