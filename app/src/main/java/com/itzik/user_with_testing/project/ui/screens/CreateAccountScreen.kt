package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
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
import com.itzik.user_with_testing.project.models.Gender
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.Light_Orange
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
    RoundedBackGround(
        topColor = Dark_Green,
        bottomColor = Turquoise
    )

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


                val (userName, email, password, genderTitle, genderBox, birthDateTitle, birthDateRow, phoneNumber, createUserBtn) = createRefs()

                val genders = listOf(Gender.MALE, Gender.FEMALE, Gender.OTHER)
                var selectedGender by remember { mutableStateOf(genders[0]) }
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
                var createPasswordLabelMessage by remember { mutableStateOf(createdPasswordText) }
                var isCreatePasswordError by remember { mutableStateOf(false) }
                var isCreatedPasswordVisible by remember { mutableStateOf(false) }


                var dayOfMonthText = stringResource(id = R.string.day)
                val dayOfMonthLabelMessage by remember { mutableStateOf(dayOfMonthText) }
                var dayOfMonth by remember { mutableStateOf("") }
                var isDayOfMonthError by remember { mutableStateOf(false) }


                var monthText = stringResource(id = R.string.month)
                val monthLabelMessage by remember { mutableStateOf(monthText) }
                var month by remember { mutableStateOf("") }
                var isMonthError by remember { mutableStateOf(false) }


                var yearText = stringResource(id = R.string.year)
                val yearLabelMessage by remember { mutableStateOf(yearText) }
                var year by remember { mutableStateOf("") }
                var isYearError by remember { mutableStateOf(false) }

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
                    isError = isFullNameError, visualTransformation = VisualTransformation.None,
                    tint = Turquoise,

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
                    visualTransformation = VisualTransformation.None, tint = Turquoise
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

                Text(
                    modifier = Modifier
                        .constrainAs(genderTitle) {
                            start.linkTo(parent.start)
                            top.linkTo(password.bottom)
                        }
                        .padding(horizontal = 32.dp, vertical = 8.dp),
                    text = stringResource(id = R.string.choose_gender),
                    color = Dark_Green,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(genderBox) {
                            top.linkTo(genderTitle.bottom)
                        }
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    genders.forEach {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    selectedGender = it
                                }
                                .padding(4.dp)
                                .size(100.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            ConstraintLayout(
                                modifier = Modifier.size(90.dp)
                            ) {
                                val (genderButton, genderName, genderIcon) = createRefs()
                                RadioButton(
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Light_Orange,
                                        unselectedColor = Turquoise
                                    ),
                                    modifier = Modifier.constrainAs(genderButton) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    }, selected = (it == selectedGender),
                                    onClick = { selectedGender = it }
                                )
                                Text(
                                    modifier = Modifier
                                        .constrainAs(genderName) {
                                            top.linkTo(parent.top)
                                            end.linkTo(parent.end)
                                        }
                                        .padding(end = 2.dp, top = 12.dp),
                                    text = it.name,
                                    fontSize = 16.sp,
                                    color = Black
                                )

                                Icon(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 8.dp)
                                        .constrainAs(genderIcon) {
                                            bottom.linkTo(parent.bottom)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                        },
                                    tint = Turquoise,
                                    imageVector = it.icon,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
                Text(
                    modifier = Modifier
                        .constrainAs(birthDateTitle) {
                            start.linkTo(parent.start)
                            top.linkTo(genderBox.bottom)
                        }
                        .padding(horizontal = 32.dp, vertical = 8.dp),
                    text = stringResource(id = R.string.birthdate_title),
                    color = Dark_Green,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .constrainAs(birthDateRow) {
                            top.linkTo(birthDateTitle.bottom)
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                ) {

                    OutlinedTextField(value = dayOfMonth, onValueChange = {
                        dayOfMonth = it
                    }, label = {
                        Text(text = dayOfMonthLabelMessage)
                    }
                        , modifier=Modifier.width(90.dp).height(60.dp))


                    OutlinedTextField(value = month, onValueChange = {
                        month = it
                    }, label = {
                        Text(text = monthLabelMessage)
                    }, modifier=Modifier.width(90.dp).height(60.dp))


                    OutlinedTextField(value = year, onValueChange = {
                        year = it
                    }, label = {
                        Text(text = yearLabelMessage)
                    }, modifier=Modifier.width(90.dp).height(60.dp))
                }
            }
        }
    }
}
