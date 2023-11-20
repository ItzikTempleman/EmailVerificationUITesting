package com.itzik.user_with_testing.project.ui.screen_sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Transform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.Light_Purple
import com.itzik.user_with_testing.project.ui.semantics.DateOutlinedTextField
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CreateUserBottomHalf(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {
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

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
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
            color = Color.White,
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
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
                modifier = Modifier.weight(1f)
            )

            DateOutlinedTextField(
                value = month,
                thisValueChange = {
                    month = it
                },
                label = monthLabelMessage,
                visualTransformation = VisualTransformation.None,
                isError = isMonthError,
                modifier = Modifier.weight(1f)
            )

            DateOutlinedTextField(
                value = year,
                thisValueChange = {
                    year = it
                },
                label = yearLabelMessage,
                visualTransformation = VisualTransformation.None,
                isError = isYearError,
                modifier = Modifier.weight(1f)
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
            modifier = Modifier
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
            modifier = Modifier
                .constrainAs(createUserBtn) {
                    top.linkTo(newPhoneNumberTF.bottom)
                }
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            onClick = {
//                moveToHomeScreen(
//                    isUsernameValid(fullName) &&
//                            isNewEmailValid(createEmail) &&
//                            isNewPasswordValid(createPassword) &&
//                            isGenderValid(genders) &&
//                            isAgeValid(age) &&
//                            isNewPhoneNumValid(newPhoneNumber),
//                    navHostController
//                )
            },
            buttonColor = Light_Purple,
            text = stringResource(id = R.string.create_account)
        )
    }
}