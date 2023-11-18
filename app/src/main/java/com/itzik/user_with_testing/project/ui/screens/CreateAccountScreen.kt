package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.navigation.Turquoise
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.ui.shapes.RoundedBackGround
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CreateAccountScreen(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {
    RoundedBackGround(Dark_Green)

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (backBtn, title) = createRefs()


        GenericRoundedButton(
            modifier = modifier
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(20.dp),
            imageVector = Icons.Default.ArrowBack,
            onClickFunction = {
                navHostController.navigate(LoginGraph.LoginPage.route)
            },
            tint = White
        )
        Text(
            text = stringResource(id = R.string.create_new),
            modifier = modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(backBtn.bottom)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 20.dp),
            color = White,
            fontSize = 32.sp
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, top = 130.dp, end = 20.dp, bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(20.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {

                val (userName, email, password, genderBox, phoneNumber) = createRefs()

                val fullNameText = stringResource(id = R.string.full_name)
                val fullNameLabelMessage by remember { mutableStateOf(fullNameText) }
                var fullName by remember { mutableStateOf("") }
                var isFullNameError by remember { mutableStateOf(false) }


                var createEmail by remember { mutableStateOf("") }
                val createEmailText = stringResource(id = R.string.create_email)
                var createEmailLabelMessage by remember { mutableStateOf(createEmailText) }
                var isNewEmailError by remember { mutableStateOf(false) }



                val createdPasswordText = stringResource(id = R.string.create_password)
                var createPassword by remember { mutableStateOf("") }

                var createPasswordLabelMessage by remember {
                    mutableStateOf(createdPasswordText)
                }
                var isCreatePasswordError by remember { mutableStateOf(false) }
                var isCreatedPasswordVisible by remember { mutableStateOf(false) }

                GenericOutlinedTextField(
                    isTrailingIconExist = false,
                    value = fullName,
                    thisValueChange = {
                        fullName = it
                    },
                    label = fullNameLabelMessage,
                    modifier = modifier
                        .constrainAs(userName) {
                            top.linkTo(parent.top)
                        }
                        .padding(vertical = 8.dp, horizontal = 20.dp),
                    imageVector = Icons.Default.Person,
                    isKeyboardPasswordType = false,
                    isIconClickable = false,
                    isError = isFullNameError, visualTransformation = VisualTransformation.None
                , tint = Turquoise,

                )

                GenericOutlinedTextField(
                    isTrailingIconExist = false,
                    value = createEmail,
                    thisValueChange = {
                        createEmail = it
                    },
                    label = createEmailLabelMessage,
                    modifier = modifier
                        .constrainAs(email) {
                            top.linkTo(userName.bottom)
                        }
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    imageVector = Icons.Default.Email,
                    isKeyboardPasswordType = false,
                    isIconClickable = false,
                    isError = isNewEmailError,
                    visualTransformation = VisualTransformation.None
                    , tint = Turquoise
                )

                GenericOutlinedTextField(
                    value = createPassword,
                    thisValueChange = {
                        createPassword = it
                    },
                    label = createPasswordLabelMessage,
                    modifier = modifier
                        .constrainAs(password) {
                            top.linkTo(email.bottom)
                        }
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    imageVector = if (isCreatedPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    isError = isCreatePasswordError,
                    isKeyboardPasswordType = true,
                    isTrailingIconExist = false,
                    isIconClickable = true,
                    isPasswordToggleClicked = isCreatedPasswordVisible,
                    isPasswordIconShowing = {
                        isCreatedPasswordVisible = !isCreatedPasswordVisible

                    },
                    visualTransformation = if (isCreatedPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                     tint = Turquoise
                )
            }
        }
    }
}