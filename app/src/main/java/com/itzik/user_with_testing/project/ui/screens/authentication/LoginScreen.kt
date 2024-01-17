package com.itzik.user_with_testing.project.ui.screens.authentication


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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.ui.navigation.ScreenContainer
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.ui.semantics.RoundedBackGround
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue
import com.itzik.user_with_testing.project.utils.Constants.Light_Blue
import com.itzik.user_with_testing.project.utils.Constants.Light_Turquoize
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope
) {
    RoundedBackGround(topColor = Light_Turquoize, bottomColor = White)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
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
            modifier = Modifier
                .padding(top = 70.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            text = stringResource(id = R.string.log_in),
            color = Dark_Blue,
            fontSize = 34.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )



        GenericOutlinedTextField(
            value = email,
            thisValueChange = {
                email = it
            },
            label = emailLabelMessage,
            modifier = Modifier
                .constrainAs(emailTF) {
                    top.linkTo(title.bottom)
                }
                .testTag("emailTextField")
                .padding(20.dp),
            imageVector = Icons.Default.Email,
            isError = isEmailError,
            visualTransformation = VisualTransformation.None,
            tint = Light_Blue,
            trailingImageVector = Icons.Default.Image,
        )

        GenericOutlinedTextField(
            value = password,
            thisValueChange = {
                password = it
            },
            label = passwordLabelMessage,
            modifier = Modifier
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
            tint = Light_Blue,
            trailingImageVector = Icons.Default.Image,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(iconRow) {
                    top.linkTo(passwordTF.bottom)
                }
                .padding(20.dp),
        ) {
            IconButton(
                onClick = {

                },
                modifier = Modifier
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
                modifier = Modifier
                    .width(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null
                )
            }
        }


        GenericButton(
            modifier = Modifier
                .constrainAs(loginBtn) {
                    top.linkTo(iconRow.bottom)
                }
                .testTag("validationButton")
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp),
            onClick = {


                if (!appViewModel.isValidLoginEmail(email)) {
                    isEmailError = true
                    emailLabelMessage = "Invalid email"
                } else isEmailError = false



                if (!appViewModel.isValidLoginPassword(password)) {
                    isPasswordError = true
                    passwordLabelMessage = "Invalid password"
                } else isPasswordError = false


                Toast.makeText(
                    context,
                    if (appViewModel.isValidLoginEmail(email) && appViewModel.isValidLoginPassword(
                            password
                        )
                    ) "Successfully logged in" else "Incorrect data, please fix",
                    Toast.LENGTH_SHORT
                ).show()
                coroutineScope.launch {
                    appViewModel.getUserFromUserNameAndPassword(email,password).collect{
                        if (it != null) {
                            it.isSignedIn=true
                            appViewModel.updateUser(it)
                            Log.d("TAG", "$it")

                            appViewModel.moveToHomeScreen(appViewModel.isTextFieldsLoginValidFormat(), navController , it)
                        }else Toast.makeText(
                            context,
                            "User does not exist",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            },
            buttonColor = Light_Blue,
            text = stringResource(id = R.string.go),
            roundedRadius = 36.dp,
            textColor = White,
            fontSize=24.sp
        )
        TextButton(
            onClick = {
                isEnterPhoneNumberDisplayed = !isEnterPhoneNumberDisplayed
            },
            modifier = Modifier
                .constrainAs(forgotPasswordText) {
                    top.linkTo(loginBtn.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = 20.dp, vertical = 8.dp),
        ) {
            Text(
                text = stringResource(id = R.string.forgot),
                color = Dark_Blue,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }

        if (isEnterPhoneNumberDisplayed) {
            GenericOutlinedTextField(
                tint = Dark_Blue,
                isTrailingIconExist = true,
                value = phone,
                thisValueChange = {
                    phone = it
                },
                label = phoneLabelMessage,
                modifier = Modifier
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
            modifier = Modifier
                .constrainAs(or) {
                    top.linkTo(loginBtn.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(top = 240.dp),
            text = stringResource(id = R.string.alternative),
            color = Dark_Blue,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = {
                navController.navigate(ScreenContainer.Registration.route)
            },
            modifier = Modifier
                .constrainAs(signUp) {
                    top.linkTo(or.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                color = Dark_Blue,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }

        GenericRoundedButton(
            modifier = Modifier
                .constrainAs(signUpBtn) {
                    top.linkTo(signUp.top)
                    bottom.linkTo(signUp.bottom)
                    start.linkTo(signUp.end)
                },
            imageVector = Icons.Default.ArrowForward,
            onClickFunction = {
                navController.navigate(ScreenContainer.Registration.route)
            },
            outerTint = Light_Blue,
            iconTint = White,
            innerIconColor = Light_Blue,
            1.2.dp
        )
    }
}