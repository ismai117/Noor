package com.example.noor.android.authentication.register.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.noor.android.R
import com.example.noor.android.authentication.utils.ValidationResult
import com.example.noor.android.main.components.LoadingBar
import com.example.noor.android.main.components.TopBar
import com.example.noor.android.navigation.screens.auth.AuthScreens
import com.example.noor.authentication.utils.AuthServiceResult
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = koinViewModel(),
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val hideSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    val state = registerViewModel.state

    val isLoading = registerViewModel.isLoading.value

    val result = registerViewModel.registerState.collectAsState().value

    LaunchedEffect(registerViewModel) {
        when (result) {
            is AuthServiceResult.Failure -> {
                scaffoldState.snackbarHostState.showSnackbar("${result.message}")
            }
            is AuthServiceResult.Success -> {
                scaffoldState.snackbarHostState.showSnackbar("${result.UID}")
            }
            else -> {}
        }
    }

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

                val (registerLayout, footer) = createRefs()

                Column(
                    modifier = modifier
                        .wrapContentSize()
                        .padding(top = 44.dp)
                        .constrainAs(registerLayout) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Register with Noor",
                        color = Color(0xFFFFD300),
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.padding(12.dp))
                    TextField(
                        value = state.username,
                        onValueChange = {
                            registerViewModel.onEvent(RegistrationEvent.UsernameChanged(it))
                        },
                        label = {
                            Text(text = "Username")
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
                    if (state.usernameError?.isNotBlank() == true) {
                        Box(
                            modifier = modifier
                                .width(331.dp)
                        ) {
                            Text(
                                text = state.usernameError,
                                color = Color(0xFFFFD300)
                            )
                        }
                    }
                    TextField(
                        value = state.email,
                        onValueChange = {
                            registerViewModel.onEvent(RegistrationEvent.EmailChanged(it))
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
                    TextField(
                        value = state.password,
                        onValueChange = {
                            registerViewModel.onEvent(RegistrationEvent.PasswordChanged(it))
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
                    if (
                        state.passwordError?.isNotBlank() == true
                    ) {
                        Box(
                            modifier = modifier
                                .width(331.dp)
                        ) {
                            Text(
                                text = state.passwordError,
                                color = Color(0xFFFFD300)
                            )
                        }
                    }
                    TextField(
                        value = state.repeatPassword,
                        onValueChange = {
                            registerViewModel.onEvent(RegistrationEvent.RepeatedPasswordChanged(it))
                        },
                        label = {
                            Text(text = "Confirm Password")
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
                    if (state.repeatPasswordError?.isNotBlank() == true) {
                        Box(
                            modifier = modifier
                                .width(331.dp)
                        ) {
                            Text(
                                text = state.repeatPasswordError,
                                color = Color(0xFFFFD300)
                            )
                        }
                    }
                    Button(
                        onClick = {
                            registerViewModel.onEvent(RegistrationEvent.Submit)
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
                            text = "Register"
                        )
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
                            append("Already have an account?")
                        }
                        append(" ")
                        withStyle(SpanStyle(color = Color.White)) {
                            pushStringAnnotation("login", "Login")
                            append("Login")
                        }
                    }
                    ClickableText(
                        text = annotatedString,
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(offset, offset)
                                .firstOrNull()?.let {
                                    navController.navigate(AuthScreens.LoginScreen.route)
                                }
                        }
                    )
                }

            }
            LoadingBar(isLoading = isLoading)
        }
    }
}