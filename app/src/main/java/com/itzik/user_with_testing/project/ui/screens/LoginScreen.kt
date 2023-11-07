package com.itzik.user_with_testing.project.ui.screens


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun LoginScreen(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel
) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        var isEmailError by remember { mutableStateOf(false) }
        var emailLabelMessage by remember { mutableStateOf("Enter email") }

        var isPasswordError by remember { mutableStateOf(false) }
        var passwordLabelMessage by remember { mutableStateOf("Enter password") }

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var isPasswordVisible by remember { mutableStateOf(false) }

        Text(
            modifier = Modifier.padding(20.dp),
            text = stringResource(id = R.string.app_name),
            color = colorResource(id = R.color.custom_blue),
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(singleLine = true,
            modifier = Modifier
                .testTag("emailTextField")
                .fillMaxWidth()
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
                    imageVector = Icons.Default.Email, contentDescription = null
                )
            })

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
                        contentDescription = null,
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    )
                }
            },
            singleLine = true,
            modifier = Modifier
                .testTag("passwordTextField")
                .fillMaxWidth()
                .padding(20.dp),
            value = password,
            onValueChange = {
                password = it
            })

        Button(modifier = Modifier
            .testTag("validationButton")
            .fillMaxWidth()
            .padding(20.dp),
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
                text = stringResource(id = R.string.validate), fontSize = 24.sp
            )
        }
    }
}

fun isEmailValid(email: String): Boolean =
    email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())


fun isPasswordValid(password: String): Boolean = password.isNotBlank() && password.length > 9


fun loginMessage(context: Context, isSuccessfulData: Boolean) {
    Toast.makeText(
        context,
        if (isSuccessfulData) "Successfully logged in" else "Incorrect data, please fix",
        Toast.LENGTH_SHORT
    ).show()
}
