package com.example.noor.android.authentication.forgetPassword.presentation


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noor.android.R
import com.example.noor.android.authentication.register.presentation.RegistrationEvent
import com.example.noor.android.main.components.TopBar
import com.example.noor.android.navigation.screens.auth.AuthScreens
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    forgetPasswordViewModel: ForgetPasswordViewModel = koinViewModel(),
    navController: NavController,
) {

    val scaffoldState = rememberScaffoldState()
    val hideSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    val state = forgetPasswordViewModel.state

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                modifier = modifier,
                leadingIcon = Icons.Filled.ArrowBack,
                title = "",
                trailingIcon = null,
                leadingIconClicked = {
                    navController.navigate(AuthScreens.LoginScreen.route)
                },
                trailingIconClicked = {

                }
            )
        },
        backgroundColor = Color.Transparent
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
                modifier = modifier
                    .wrapContentSize()
                    .padding(top = 44.dp)
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Reset Password",
                    color = Color(0xFFFFD300),
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(modifier = modifier.width(331.dp)) {
                    Text(
                        text = "Please enter the email address you used to set up your Noor account.",
                        color = Color(0xFFC0C0C0)
                    )
                }
                Spacer(modifier = modifier.padding(4.dp))
                TextField(
                    value = state.email,
                    onValueChange = {
                        forgetPasswordViewModel.onEvent(ForgetPasswordEvent.EmailChanged(it))
                    },
                    label = {
                        Text(text = "Email")
                    },
                    modifier = modifier
                        .width(331.dp)
                        .height(56.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        hideSoftwareKeyboardController?.hide()
                    })
                )
                if (state.emailError?.isNotBlank() == true) {
                    Box(
                        modifier = modifier
                            .width(331.dp)
                    ) {
                        Text(
                            text = state.emailError,
                            color = Color(0xFFFFD300)
                        )
                    }
                }
                Button(
                    onClick = {
                        forgetPasswordViewModel.onEvent(ForgetPasswordEvent.Submit)
                    },
                    modifier = modifier
                        .width(331.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFFFD300),
                        contentColor = Color(0xFF000000),
                    )
                ) {
                    Text(
                        text = "Submit"
                    )
                }
            }
        }
    }

}
