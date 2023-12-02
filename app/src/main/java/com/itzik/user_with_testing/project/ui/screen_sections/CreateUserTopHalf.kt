package com.itzik.user_with_testing.project.ui.screen_sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
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
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CreateUserTopHalf(

    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {


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
    val genders = listOf(Gender.MALE, Gender.FEMALE, Gender.OTHER)
    var selectedGender by remember { mutableStateOf(genders[0]) }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
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
                modifier = Modifier
                    .constrainAs(userName) {
                        top.linkTo(parent.top)
                    }
                    .padding(vertical = 8.dp),
                imageVector = Icons.Default.Person,
                isKeyboardPasswordType = false,
                isIconClickableParam = false,
                isError = isFullNameError, visualTransformation = VisualTransformation.None,
                tint = Dark_Green,
                trailingImageVector = Icons.Default.Image,
                phoneNumberTFOuterLabel = {}
            )
            if (fullName.length > 9) {
                userViewModel.splitUserNameIntoFirstAndFamilyName(fullName)
            }

            GenericOutlinedTextField(
                isTrailingIconExist = false,
                value = createEmail,
                thisValueChange = {
                    createEmail = it
                },
                label = createEmailLabelMessage,
                modifier = Modifier
                    .constrainAs(email) {
                        top.linkTo(userName.bottom)
                    }
                    .padding(vertical = 8.dp),
                imageVector = Icons.Default.Email,
                isKeyboardPasswordType = false,
                isIconClickableParam = false,
                isError = isNewEmailError,
                visualTransformation = VisualTransformation.None, tint = Dark_Green,
                trailingImageVector = Icons.Default.Image,
                phoneNumberTFOuterLabel = {}
            )

            GenericOutlinedTextField(
                value = createPassword,
                thisValueChange = {
                    createPassword = it
                },
                label = createPasswordLabelMessage,
                modifier = Modifier
                    .constrainAs(password) {
                        top.linkTo(email.bottom)
                    }
                    .padding(vertical = 8.dp),
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
                tint = Dark_Green,
                trailingImageVector = Icons.Default.Image,
                phoneNumberTFOuterLabel = {}
            )

            Text(
                modifier = Modifier
                    .constrainAs(genderTitle) {
                        start.linkTo(parent.start)
                        top.linkTo(password.bottom)
                    }
                    .padding(horizontal = 12.dp, vertical = 8.dp),
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
                    .padding(vertical = 8.dp),
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
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        ConstraintLayout(
                            modifier = Modifier.size(90.dp)
                        ) {
                            val (genderButton, genderName, genderIcon) = createRefs()
                            RadioButton(
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Dark_Green,
                                    unselectedColor = Dark_Green
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
                                tint = Dark_Green,
                                imageVector = it.icon,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}