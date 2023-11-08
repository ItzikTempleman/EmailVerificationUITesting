package com.itzik.user_with_testing.project.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
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
import com.itzik.user_with_testing.project.utils.isEmailValid
import com.itzik.user_with_testing.project.utils.isPasswordValid
import com.itzik.user_with_testing.project.utils.loginMessage
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
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (title, emailTF, passwordTF, loginBtn, forgotPasswordText, iconRow, signUp) = createRefs()

        val context = LocalContext.current
        val emailText = stringResource(id = R.string.enter_email)
        val passwordText = stringResource(id = R.string.enter_password)
        var isEmailError by remember { mutableStateOf(false) }
        var emailLabelMessage by remember { mutableStateOf(emailText) }

        var isPasswordError by remember { mutableStateOf(false) }
        var passwordLabelMessage by remember { mutableStateOf(passwordText) }

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var isPasswordVisible by remember { mutableStateOf(false) }

        Text(
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            text = stringResource(id = R.string.welcome),
            color = DefaultBlue,
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )


        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
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
            },
            isError = isEmailError,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = DefaultBlue
                )
            }
        )

        OutlinedTextField(keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
            label = {
                Text(text = passwordLabelMessage)
            },
            isError = isPasswordError,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        contentDescription = null, tint = DefaultBlue,
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    )
                }
            },
            singleLine = true,
            modifier = Modifier
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
        Text(
            modifier = Modifier
                .clickable {

                }
                .constrainAs(forgotPasswordText) {
                    top.linkTo(passwordTF.bottom)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 50.dp, vertical = 8.dp),
            text = stringResource(id = R.string.forgot),
            color = colorResource(id = R.color.custom_blue),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(iconRow) {
                    top.linkTo(forgotPasswordText.bottom)
                }
                .padding(30.dp),
        ) {
            Image(
                modifier = Modifier
                    .width(40.dp)
                    .clickable {

                    },
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .width(40.dp)
                    .clickable {

                    },
                painter = painterResource(id = R.drawable.google),
                contentDescription = null
            )
        }





        Button(
            shape = CutCornerShape(percent = 8),
            modifier = Modifier
                .constrainAs(loginBtn) {
                    top.linkTo(iconRow.bottom)
                }
                .testTag("validationButton")
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end=20.dp),
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
            }) {
            Text(
                text = stringResource(id = R.string.log_in), fontSize = 24.sp
            )
        }


        Text(
            modifier = Modifier
                .clickable {
                    navHostController.navigate(LoginGraph.FirstRegistrationPage.route)
                }
                .constrainAs(signUp) {
                    top.linkTo(loginBtn.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }.padding(8.dp),
            text = stringResource(id = R.string.sign_up),
            color = DefaultBlue,
            fontSize = 22.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
    }
}

