package com.itzik.user_with_testing.project.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.ui.shapes.Blue
import com.itzik.user_with_testing.project.ui.shapes.RoundedBackGround
import com.itzik.user_with_testing.project.ui.shapes.Turquoise
import com.itzik.user_with_testing.project.ui.shapes.Yellow
import com.itzik.user_with_testing.project.utils.isEmailValid
import com.itzik.user_with_testing.project.utils.isPasswordValid
import com.itzik.user_with_testing.project.utils.loginMessage
import com.itzik.user_with_testing.project.utils.moveToHomeScreen
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel?,
) {
    RoundedBackGround(Turquoise)
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {

        val (title) = createRefs()
        Text(
            modifier = modifier
                .padding(top = 70.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            text = stringResource(id = R.string.log_in),
            color = Color.White,
            fontSize = 32.sp
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 130.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(20.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (emailTF, passwordTF, loginBtn, forgotPasswordText, iconRow, signUp, phoneNumberOutlinedTF, or, signUpBtn) = createRefs()

                val context = LocalContext.current

                var email by remember { mutableStateOf("") }
                val emailText = stringResource(id = R.string.enter_email)
                var emailLabelMessage by remember { mutableStateOf(emailText) }
                var isEmailError by remember { mutableStateOf(false) }


                val passwordText = stringResource(id = R.string.enter_password)
                var passwordLabelMessage by remember { mutableStateOf(passwordText) }
                var password by remember { mutableStateOf("") }
                var isPasswordError by remember { mutableStateOf(false) }


                var isPasswordVisible by remember { mutableStateOf(false) }


                var isEnterPhoneNumberDisplayed by remember { mutableStateOf(false) }

                var phone by remember { mutableStateOf("") }
                val phoneText = stringResource(id = R.string.enter_phone_number)
                var phoneLabelMessage by remember { mutableStateOf(phoneText) }




                GenericOutlinedTextField(
                    value = email,
                    thisValueChange = {
                        email = it
                    },
                    label = emailLabelMessage,
                    modifier = modifier
                        .constrainAs(emailTF) {
                            top.linkTo(parent.top)
                        }
                        .testTag("emailTextField")
                        .padding(20.dp),
                    imageVector = Icons.Default.Email,
                    isError = isEmailError,
                    isKeyboardPasswordType = false,
                    isTrailingIconExist = false,
                    isIconClickable = false,
                    visualTransformation = VisualTransformation.None
                )

                GenericOutlinedTextField(
                    value = password,
                    thisValueChange = {
                        password = it
                    },
                    label = passwordLabelMessage,
                    modifier = modifier
                        .constrainAs(passwordTF) {
                            top.linkTo(emailTF.bottom)
                        }
                        .testTag("passwordTextField")
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    isError = isPasswordError,
                    isKeyboardPasswordType = true,
                    isTrailingIconExist = false,
                    isIconClickable = true,
                    isPasswordToggleClicked = isPasswordVisible,
                    isPasswordIconShowing = {
                        isPasswordVisible = !isPasswordVisible

                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                )


                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(iconRow) {
                            top.linkTo(passwordTF.bottom)
                        }
                        .padding(20.dp),
                ) {
                    Image(
                        modifier = modifier
                            .width(40.dp)
                            .clickable {

                            },
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null
                    )
                    Image(
                        modifier = modifier
                            .width(40.dp)
                            .clickable {

                            },
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null
                    )
                }

                Button(
                    shape = RoundedCornerShape(12.dp),
                    modifier = modifier
                        .constrainAs(loginBtn) {
                            top.linkTo(iconRow.bottom)
                        }
                        .testTag("validationButton")
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 8.dp, end = 20.dp),
                    onClick = {
                        if (!isEmailValid(email)) {
                            isEmailError = true
                            emailLabelMessage = "Invalid email"
                        } else isEmailError = false
                        if (!isPasswordValid(password)) {
                            isPasswordError = true
                            passwordLabelMessage = "Invalid password"
                        } else isPasswordError = false
                        loginMessage(context, isEmailValid(email) && isPasswordValid(password))
                        moveToHomeScreen(
                            isEmailValid(email) && isPasswordValid(password),
                            navHostController
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Yellow,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.go),
                        fontSize = 24.sp
                    )
                }

                Text(
                    modifier = modifier
                        .clickable {
                            isEnterPhoneNumberDisplayed = !isEnterPhoneNumberDisplayed
                        }
                        .constrainAs(forgotPasswordText) {
                            top.linkTo(loginBtn.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .padding(20.dp),
                    text = stringResource(id = R.string.forgot),
                    color = Black,
                    fontSize = 20.sp,
                )

                if (isEnterPhoneNumberDisplayed) {

                    GenericOutlinedTextField(
                        isTrailingIconExist = false,
                        value = phone,
                        thisValueChange = {
                            phone = it
                        },
                        label = phoneLabelMessage,
                        modifier = modifier
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                            .constrainAs(phoneNumberOutlinedTF) {
                                top.linkTo(forgotPasswordText.bottom)
                            },
                        imageVector = Icons.Default.Smartphone,
                        isKeyboardPasswordType = false,
                        isIconClickable = false,
//                        visualTransformation = VisualTransformation.None,
//                        trailingImageVector = Icons.Default.Send,
//                        phoneNumberTFOuterLabel = {
//
//                        },
//                        phoneNumberTFInnerText = {
//                            phoneNumber=it
//                            phoneNumberValue=it
//                        }
                    )
                }

//                    OutlinedTextField(
//                        singleLine = true,
//                        label = {
//                            Text(text = phoneLabelMessage)
//                        },
//                        modifier = modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp, vertical = 8.dp)
//                            .constrainAs(phoneNumberOutlinedTF) {
//                                top.linkTo(forgotPasswordText.bottom)
//                            },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = Blue,
//                            unfocusedBorderColor = Color.DarkGray,
//                            backgroundColor = White
//                        ),
//                        value = phone,
//                        onValueChange = {
//                            phone = it
//                        },
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.Smartphone,
//                                contentDescription = null,
//                                tint = Blue
//                            )
//                        },
//                        trailingIcon = {
//                            IconButton(onClick = {
//                                phoneLabelMessage = "Reset text message sent"
//                                phone = "Enter the code sent to your number"
//                            }) {
//                                Icon(
//                                    contentDescription = null, tint = Blue,
//                                    imageVector = Icons.Default.Send
//                                )
//                            }
//                        }
//                    )


                Text(
                    modifier = modifier
                        .constrainAs(or) {
                            top.linkTo(loginBtn.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .padding(top = 160.dp),
                    text = stringResource(id = R.string.or),
                    color = Black,
                    fontSize = 14.sp
                )

                Text(
                    modifier = modifier
                        .constrainAs(signUp) {
                            top.linkTo(or.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .padding(vertical = 20.dp, horizontal = 8.dp),
                    text = stringResource(id = R.string.sign_up),
                    color = Blue,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                GenericRoundedButton(modifier = modifier
                    .constrainAs(signUpBtn) {
                        top.linkTo(signUp.top)
                        bottom.linkTo(signUp.bottom)
                        start.linkTo(signUp.end)
                    }
                    .padding(2.dp),
                    imageVector = Icons.Default.ArrowForward,
                    onClickFunction = {
                        navHostController.navigate(LoginGraph.CreateAccountPage.route)
                    }
                )
            }
        }
    }
}
//}

