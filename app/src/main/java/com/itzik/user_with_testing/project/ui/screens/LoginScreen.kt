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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.shapes.Blue
import com.itzik.user_with_testing.project.ui.shapes.RoundedBackGround
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
    RoundedBackGround(Blue)
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 60.dp),
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
                val (title, emailTF, passwordTF, loginBtn, forgotPasswordText, iconRow, signUp, phoneNumberOutlinedTF, or) = createRefs()
                val context = LocalContext.current

                var email by remember { mutableStateOf("") }
                val emailText = stringResource(id = R.string.enter_email)
                var emailLabelMessage by remember { mutableStateOf(emailText) }


                val passwordText = stringResource(id = R.string.enter_password)
                var passwordLabelMessage by remember { mutableStateOf(passwordText) }
                var password by remember { mutableStateOf("") }


                var isEmailError by remember { mutableStateOf(false) }
                var isPasswordVisible by remember { mutableStateOf(false) }
                var isPasswordError by remember { mutableStateOf(false) }


                var isEnterPhoneNumberDisplayed by remember { mutableStateOf(false) }

                var phoneNumberValue by remember { mutableStateOf("") }
                val phoneNumber = stringResource(id = R.string.enter_phone_number)
                var phoneNumberToResetLabel by remember {
                    mutableStateOf(phoneNumber)
                }



                Text(
                    modifier = modifier
                        .padding(top = 40.dp, start = 20.dp)
                        .constrainAs(title) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        },
                    text = stringResource(id = R.string.log_in),
                    color = Blue,
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(emailTF) {
                            top.linkTo(title.bottom)
                        }
                        .testTag("emailTextField")
                        .padding(20.dp),
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text(text = emailLabelMessage)
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Color.DarkGray,
                        backgroundColor = Color.White
                    ),
                    isError = isEmailError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = Blue
                        )
                    }
                )
                OutlinedTextField(keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ), label = {
                    Text(text = passwordLabelMessage)
                },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Color.DarkGray,
                        backgroundColor = Color.White
                    ),
                    isError = isPasswordError,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    leadingIcon = {
                        IconButton(onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }) {
                            Icon(
                                contentDescription = null, tint = Blue,
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            )
                        }
                    },
                    singleLine = true,
                    modifier = modifier
                        .constrainAs(passwordTF) {
                            top.linkTo(emailTF.bottom)
                        }
                        .testTag("passwordTextField")
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    value = password,
                    onValueChange = {
                        password = it
                    })

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(iconRow) {
                            top.linkTo(passwordTF.bottom)
                        }
                        .padding(30.dp),
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
                        contentColor = Blue
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.log_in), fontSize = 24.sp,
                        fontStyle = FontStyle.Italic

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
                        }
                        .padding(start = 40.dp, end = 40.dp, top = 30.dp),
                    text = stringResource(id = R.string.forgot),
                    color = Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )

                if (isEnterPhoneNumberDisplayed) {

                    OutlinedTextField(
                        singleLine = true,
                        label = {
                            Text(text = phoneNumberToResetLabel)
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp)
                            .constrainAs(phoneNumberOutlinedTF) {
                                top.linkTo(forgotPasswordText.bottom)
                            },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Color.DarkGray,
                            backgroundColor = Color.White
                        ),
                        value = phoneNumberValue,
                        onValueChange = {
                            phoneNumberValue = it
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Smartphone,
                                contentDescription = null,
                                tint = Blue
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                phoneNumberToResetLabel = "Reset text message sent"
                                phoneNumberValue = "Enter the code sent to your number"
                            }) {
                                Icon(
                                    contentDescription = null, tint = Blue,
                                    imageVector = Icons.Default.Send
                                )
                            }
                        }
                    )

                }

                Text(
                    modifier = modifier
                        .constrainAs(or) {
                            top.linkTo(loginBtn.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .padding(top = 180.dp),
                    text = "----------------------  OR  ------------------------",
                    color = Blue,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = modifier
                        .clickable {
                            navHostController.navigate(LoginGraph.CreateAccountPage.route)
                        }
                        .constrainAs(signUp) {
                            top.linkTo(or.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                        .padding(8.dp),
                    text = stringResource(id = R.string.sign_up),
                    color = Blue,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )


            }
        }
    }
}
