package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Transform
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
import com.itzik.user_with_testing.project.navigation.Light_Purple
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.navigation.Turquoise
import com.itzik.user_with_testing.project.ui.semantics.DateOutlinedTextField
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.ui.shapes.RoundedBackGround
import com.itzik.user_with_testing.project.utils.isAgeValid
import com.itzik.user_with_testing.project.utils.isGenderValid
import com.itzik.user_with_testing.project.utils.isNewEmailValid
import com.itzik.user_with_testing.project.utils.isNewPasswordValid
import com.itzik.user_with_testing.project.utils.isNewPhoneNumValid
import com.itzik.user_with_testing.project.utils.isUsernameValid
import com.itzik.user_with_testing.project.utils.moveToHomeScreen
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
        val (backBtn, title, basicInfoCard, dateAndPhoneAndContinueBtn) = createRefs()

        val genders = listOf(Gender.MALE, Gender.FEMALE, Gender.OTHER)
        var selectedGender by remember { mutableStateOf(genders[0]) }
        val fullNameText = stringResource(id = R.string.full_name)
        val fullNameLabelMessage by remember { mutableStateOf(fullNameText) }
        var fullName by remember { mutableStateOf("") }
        val isFullNameError by remember { mutableStateOf(false) }
        var createEmail by remember { mutableStateOf("") }
        val createEmailText = stringResource(id = R.string.create_email)
        val createEmailLabelMessage by remember { mutableStateOf(createEmailText) }
        val isNewEmailError by remember { mutableStateOf(false) }
        val createdPasswordText = stringResource(id = R.string.create_password)
        var createPassword by remember { mutableStateOf("") }
        val createPasswordLabelMessage by remember { mutableStateOf(createdPasswordText) }
        val isCreatePasswordError by remember { mutableStateOf(false) }
        var isCreatedPasswordVisible by remember { mutableStateOf(false) }
        var dayOfMonth by remember { mutableStateOf("") }
        val dayOfMonthText = stringResource(id = R.string.day)
        val dayOfMonthLabelMessage by remember { mutableStateOf(dayOfMonthText) }
        val isDayOfMonthError by remember { mutableStateOf(false) }
        var month by remember { mutableStateOf("") }
        val monthText = stringResource(id = R.string.month)
        val monthLabelMessage by remember { mutableStateOf(monthText) }
        val isMonthError by remember { mutableStateOf(false) }
        var year by remember { mutableStateOf("") }
        val yearText = stringResource(id = R.string.year)
        val yearLabelMessage by remember { mutableStateOf(yearText) }
        val isYearError by remember { mutableStateOf(false) }
        var newPhoneNumber by remember { mutableStateOf("") }
        val newPhoneNumberText = stringResource(id = R.string.enter_new_phone_number)
        val newPhoneNumberMessage by remember { mutableStateOf(newPhoneNumberText) }
        val isNewPhoneNumberError by remember { mutableStateOf(false) }
        val age: Int = 0

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
                    top.linkTo(backBtn.top)
                    end.linkTo(parent.end)
                }
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            color = White,
            fontSize = 32.sp
        )

        Card(
            modifier = Modifier
                .constrainAs(basicInfoCard) {
                    top.linkTo(title.bottom)
                }
                .height(450.dp)
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(20.dp)
        ) {

            ConstraintLayout(modifier = modifier.fillMaxSize()) {
                val (userName, email, password, genderTitle, genderBox) = createRefs()

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
                    isIconClickableParam = false,
                    isError = isFullNameError, visualTransformation = VisualTransformation.None,
                    tint = Turquoise,
                    trailingImageVector = Icons.Default.Image,
                    phoneNumberTFOuterLabel = {}
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
                    isIconClickableParam = false,
                    isError = isNewEmailError,
                    visualTransformation = VisualTransformation.None, tint = Turquoise,
                    trailingImageVector = Icons.Default.Image,
                    phoneNumberTFOuterLabel = {}
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
                    isIconClickableParam = true,
                    isPasswordToggleClicked = isCreatedPasswordVisible,
                    isPasswordIconShowing = {
                        isCreatedPasswordVisible = !isCreatedPasswordVisible

                    },
                    visualTransformation = if (isCreatedPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    tint = Turquoise,
                    trailingImageVector = Icons.Default.Image,
                    phoneNumberTFOuterLabel = {}
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
            }
        }

        ConstraintLayout(
            modifier = Modifier
                .constrainAs(dateAndPhoneAndContinueBtn) {
                    top.linkTo(basicInfoCard.bottom)
                }
                .padding(horizontal = 20.dp),
        ) {
            val (birthDateTitle, birthDateRow, newPhoneNumberTF, createUserBtn) = createRefs()



            Text(
                modifier = Modifier
                    .constrainAs(birthDateTitle) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .padding(start = 32.dp, end = 32.dp, top = 12.dp),
                text = stringResource(id = R.string.birthdate_title),
                color = White,
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .constrainAs(birthDateRow) {
                        top.linkTo(birthDateTitle.bottom)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 4.dp),
            ) {

                DateOutlinedTextField(
                    value = dayOfMonth,
                    thisValueChange = {
                        dayOfMonth = it
                    },
                    label = dayOfMonthLabelMessage,
                    visualTransformation = VisualTransformation.None,
                    isError = isDayOfMonthError,
                    modifier = modifier.weight(1f)
                )

                DateOutlinedTextField(
                    value = month,
                    thisValueChange = {
                        month = it
                    },
                    label = monthLabelMessage,
                    visualTransformation = VisualTransformation.None,
                    isError = isMonthError,
                    modifier = modifier.weight(1f)
                )

                DateOutlinedTextField(
                    value = year,
                    thisValueChange = {
                        year = it
                    },
                    label = yearLabelMessage,
                    visualTransformation = VisualTransformation.None,
                    isError = isYearError,
                    modifier = modifier.weight(1f)
                )
            }


            GenericOutlinedTextField(
                tint = Dark_Green,
                isTrailingIconExist = false,
                value = newPhoneNumber,
                thisValueChange = {
                    newPhoneNumber = it
                },
                label = newPhoneNumberMessage,
                modifier = modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .constrainAs(newPhoneNumberTF) {
                        top.linkTo(birthDateRow.bottom)
                    },
                imageVector = Icons.Default.Smartphone,
                isError = isNewPhoneNumberError,
                isKeyboardPasswordType = false,
                isIconClickableParam = false,
                visualTransformation = VisualTransformation.None,
                trailingImageVector = Icons.Default.Transform,
                phoneNumberTFOuterLabel = {}
            )


            GenericButton(
                modifier = modifier
                    .constrainAs(createUserBtn) {
                        top.linkTo(newPhoneNumberTF.bottom)
                    }
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                onClick = {
                    moveToHomeScreen(
                        isUsernameValid(fullName) &&
                                isNewEmailValid(createEmail) &&
                                isNewPasswordValid(createPassword) &&
                                isGenderValid(genders) &&
                                isAgeValid(age) &&
                                isNewPhoneNumValid(newPhoneNumber),
                        navHostController
                    )
                },
                buttonColor = Light_Purple,
                text = stringResource(id = R.string.create_account)
            )
        }
    }
}










