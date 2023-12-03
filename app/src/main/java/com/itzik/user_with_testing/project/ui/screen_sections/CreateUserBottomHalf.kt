package com.itzik.user_with_testing.project.ui.screen_sections

import DatePickerDialogScreen
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
import androidx.compose.ui.graphics.Color.Companion.White
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
import com.itzik.user_with_testing.project.navigation.Light_Orange
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
    var newPhoneNumber by remember { mutableStateOf("") }
    val newPhoneNumberText = stringResource(id = R.string.enter_new_phone_number)
    val newPhoneNumberMessage by remember { mutableStateOf(newPhoneNumberText) }
    val isNewPhoneNumberError by remember { mutableStateOf(false) }


    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (birthDateTitle, birthDateTF, newPhoneNumberTF, createUserBtn) = createRefs()


        Text(
            text = stringResource(id = R.string.birthdate),
            modifier = Modifier
                .constrainAs(birthDateTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(top = 20.dp, start = 32.dp),
            color = Dark_Green,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        DatePickerDialogScreen(
            userViewModel = userViewModel,
            modifier = Modifier
                .constrainAs(birthDateTF) {
                    top.linkTo(birthDateTitle.bottom)
                }
                .fillMaxWidth()
                .padding(top = 8.dp, start = 12.dp, end = 12.dp),

            )



        GenericOutlinedTextField(
            tint = Dark_Green,
            isTrailingIconExist = false,
            value = newPhoneNumber,
            thisValueChange = {
                newPhoneNumber = it
            },
            label = newPhoneNumberMessage,
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(newPhoneNumberTF) {
                    top.linkTo(birthDateTF.bottom)
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
                .padding(horizontal = 12.dp, vertical = 40.dp),
            onClick = {

            },
            buttonColor = Light_Orange,
            text = stringResource(id = R.string.create_account),
            textColor = White,
            roundedRadius = 4.dp
        )
    }
}

