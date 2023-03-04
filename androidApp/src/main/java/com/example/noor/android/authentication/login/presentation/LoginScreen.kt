package com.example.noor.android.authentication.login.presentation


import android.inputmethodservice.Keyboard
import android.util.Log
import androidx.compose.foundation.*
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.noor.android.R
import com.example.noor.android.authentication.login.presentation.LoginEvent
import com.example.noor.android.authentication.login.presentation.LoginViewModel
import com.example.noor.android.authentication.register.presentation.RegistrationEvent
import com.example.noor.android.main.components.LoadingBar
import com.example.noor.android.main.components.TopBar
import com.example.noor.android.navigation.screens.auth.AuthScreens
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LogdinScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel(),
    navController: NavController,
    signIn: () -> Unit,
) {

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val hideSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    val state = loginViewModel.state

    val isLoading = loginViewModel.isLoading.value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                modifier = modifier,
                leadingIcon = Icons.Filled.ArrowBack,
                title = "",
                trailingIcon = null,
                leadingIconClicked = {
                    navController.navigate(AuthScreens.StarterScreen.route)
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
            ConstraintLayout(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                val (loginLayout, footer) = createRefs()

                Column(
                    modifier = modifier
                        .wrapContentSize()
                        .padding(top = 44.dp)
                        .constrainAs(loginLayout) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Welcome Back!",
                        color = Color(0xFFFFD300),
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.padding(12.dp))
                    TextField(
                        value = state.email,
                        onValueChange = {
                            loginViewModel.onEvent(LoginEvent.EmailChanged(it))
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
                            modifier = modifier.width(331.dp)
                        ) {
                            Text(
                                text = state.emailError,
                                color = Color(0xFFFFD300)
                            )
                        }
                    }
                    TextField(
                        value = state.password,
                        onValueChange = {
                            loginViewModel.onEvent(LoginEvent.PasswordChanged(it))
                        },
                        label = {
                            Text(text = "Password")
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
                    if (state.passwordError?.isNotBlank() == true) {
                        Box(
                            modifier = modifier.width(331.dp)
                        ) {
                            Text(
                                text = state.passwordError,
                                color = Color(0xFFFFD300)
                            )
                        }
                    }
                    Box(
                        modifier = modifier
                            .wrapContentSize()
                            .align(Alignment.End)
                            .clickable {
                                navController.navigate(AuthScreens.ForgotPasswordScreen.route)
                            }
                    ) {
                        Text(
                            text = "Forget Password?",
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = {
                            loginViewModel.onEvent(LoginEvent.Submit)
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
                            text = "Login"
                        )
                    }
                    Row(
                        modifier = modifier.width(331.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Divider(
                            color = Color.White,
                            thickness = 2.dp,
                            modifier = modifier.width(40.dp)
                        )
                        Text(
                            text = "or",
                            color = Color.White,
                            modifier = modifier.padding(start = 6.dp, end = 6.dp)
                        )
                        Divider(
                            color = Color.White,
                            thickness = 2.dp,
                            modifier = modifier.width(40.dp)
                        )
                    }
                    Button(
                        onClick = {

                        },
                        modifier = modifier
                            .width(331.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFFFD300),
                            contentColor = Color(0xFF000000),
                        )
                    ) {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = modifier.wrapContentSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.google_icon),
                                    contentDescription = "",
                                    modifier = modifier.size(24.dp)
                                )
                                Text(
                                    text = "Sign in with Google",
                                    modifier = modifier
                                        .width(170.dp)
                                )
                            }
                        }
                    }
                    Button(
                        onClick = {

                        },
                        modifier = modifier
                            .width(331.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFFFD300),
                            contentColor = Color(0xFF000000),
                        )
                    ) {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = modifier.wrapContentSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.facebook_icon),
                                    contentDescription = "",
                                    modifier = modifier.size(24.dp)
                                )
                                Text(
                                    text = "Sign in with Facebook",
                                    modifier = modifier
                                        .width(170.dp)
                                )
                            }
                        }
                    }
                    Button(
                        onClick = {

                        },
                        modifier = modifier
                            .width(331.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFFFD300),
                            contentColor = Color(0xFF000000),
                        ),
                    ) {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = modifier.wrapContentSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.apple_icon),
                                    contentDescription = "",
                                    modifier = modifier.size(24.dp)
                                )
                                Text(
                                    text = "Sign in with Apple",
                                    modifier = modifier
                                        .width(170.dp)
                                )
                            }
                        }
                    }
                }
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .constrainAs(footer) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    val annotatedString = buildAnnotatedString {
                        withStyle(SpanStyle(color = Color(0xFFFFD300))) {
                            append("Don't have an account?")
                        }
                        append(" ")
                        withStyle(SpanStyle(color = Color.White)) {
                            pushStringAnnotation("register", "Register")
                            append("Register")
                        }
                    }
                    ClickableText(
                        text = annotatedString,
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(offset, offset)
                                .firstOrNull()?.let {
                                    navController.navigate(AuthScreens.RegisterScreen.route)
                                }
                        }
                    )
                }
            }
        }
        LoadingBar(isLoading = isLoading)
    }

}