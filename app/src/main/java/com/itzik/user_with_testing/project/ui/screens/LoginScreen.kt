package com.itzik.user_with_testing.project.ui.screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
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
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.Light_Green
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.ui.semantics.RoundedBackGround
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope
) {
    RoundedBackGround(topColor = White, bottomColor = White)
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {

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
        val isPhoneNumberError by remember { mutableStateOf(false) }

        val (title, emailTF, passwordTF, loginBtn, forgotPasswordText, iconRow, signUp, phoneNumberOutlinedTF, or, signUpBtn) = createRefs()

        Text(
            modifier = modifier
                .padding(top = 70.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            text = stringResource(id = R.string.log_in),
            color = Black,
            fontSize = 32.sp
        )



        GenericOutlinedTextField(
            value = email,
            thisValueChange = {
                email = it
            },
            label = emailLabelMessage,
            modifier = modifier
                .constrainAs(emailTF) {
                    top.linkTo(title.bottom)
                }
                .testTag("emailTextField")
                .padding(20.dp),
            imageVector = Icons.Default.Email,
            isError = isEmailError,
            visualTransformation = VisualTransformation.None,
            tint = Light_Green,
            trailingImageVector = Icons.Default.Image,
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
            isIconClickableParam = true,
            isPasswordToggleClicked = isPasswordVisible,
            isPasswordIconShowing = {
                isPasswordVisible = !isPasswordVisible

            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            tint = Light_Green,
            trailingImageVector = Icons.Default.Image,
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
            IconButton(
                onClick = {

                },
                modifier = modifier
                    .width(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {

                },
                modifier = modifier
                    .width(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null
                )
            }
        }


        GenericButton(
            modifier = modifier
                .constrainAs(loginBtn) {
                    top.linkTo(iconRow.bottom)
                }
                .testTag("validationButton")
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp),
            onClick = {


                if (!userViewModel.isValidLoginEmail(email)) {
                    isEmailError = true
                    emailLabelMessage = "Invalid email"
                } else isEmailError = false



                if (!userViewModel.isValidLoginPassword(password)) {
                    isPasswordError = true
                    passwordLabelMessage = "Invalid password"
                } else isPasswordError = false


                Toast.makeText(
                    context,
                    if (userViewModel.isValidLoginEmail(email) && userViewModel.isValidLoginPassword(
                            password
                        )
                    ) "Successfully logged in" else "Incorrect data, please fix",
                    Toast.LENGTH_SHORT
                ).show()
                coroutineScope.launch {
                    userViewModel.getUserFromUserNameAndPassword(email,password).collect{
                        if (it != null) {
                            it.isSignedIn=true
                            userViewModel.updateUser(it)
                            Log.d("TAG", "$it")

                            userViewModel.moveToHomeScreen(userViewModel.isValidLoginEmail(email) && userViewModel.isValidLoginPassword(password), navHostController , it)
                        }else Toast.makeText(
                            context,
                            "User does not exist",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            },
            buttonColor = Light_Green,
            text = stringResource(id = R.string.go),
            roundedRadius = 36.dp,
            textColor = White
        )
        TextButton(
            onClick = {
                isEnterPhoneNumberDisplayed = !isEnterPhoneNumberDisplayed
            },
            modifier = modifier
                .constrainAs(forgotPasswordText) {
                    top.linkTo(loginBtn.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = 20.dp, vertical = 8.dp),
        ) {
            Text(
                text = stringResource(id = R.string.forgot),
                color = Dark_Green,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }

        if (isEnterPhoneNumberDisplayed) {
            GenericOutlinedTextField(
                tint = Dark_Green,
                isTrailingIconExist = true,
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
                isError = isPhoneNumberError,
                isIconClickableParam = true,
                visualTransformation = VisualTransformation.None,
                trailingImageVector = Icons.Default.Transform,
                phoneNumberTFOuterLabel = {
                    phoneLabelMessage = it
                },
            )
        }

        Text(
            modifier = modifier
                .constrainAs(or) {
                    top.linkTo(loginBtn.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(top = 240.dp),
            text = stringResource(id = R.string.alternative),
            color = Dark_Green,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = {
                navHostController.navigate(LoginGraph.CreateAccountPage.route)
            },
            modifier = modifier
                .constrainAs(signUp) {
                    top.linkTo(or.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                color = Dark_Green,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }

        GenericRoundedButton(
            modifier = modifier
                .constrainAs(signUpBtn) {
                    top.linkTo(signUp.top)
                    bottom.linkTo(signUp.bottom)
                    start.linkTo(signUp.end)
                },
            imageVector = Icons.Default.ArrowForward,
            onClickFunction = {
                navHostController.navigate(LoginGraph.CreateAccountPage.route)
            },
            outerTint = Dark_Green,
            iconTint = Dark_Green,
            innerIconColor = White,
            1.2.dp
        )
    }
}
