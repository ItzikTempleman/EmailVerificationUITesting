package com.itzik.user_with_testing.project.ui.screens.authentication.registration

import DatePickerDialogScreen
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.utils.Constants
import com.itzik.user_with_testing.project.utils.Constants.Light_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel


@Composable
fun RegistrationDateAndPhoneNumberScreen(
    modifier: Modifier,
    appViewModel: AppViewModel,

    ) {
    var newPhoneNumber by remember { mutableStateOf("") }
    val newPhoneNumberText = stringResource(id = R.string.enter_new_phone_number)
    val newPhoneNumberMessage by remember { mutableStateOf(newPhoneNumberText) }
    val isNewPhoneNumberError by remember { mutableStateOf(false) }


    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (birthDateTitle, birthDateTF, newPhoneNumberTF) = createRefs()


        Text(
            text = stringResource(id = R.string.birthdate),
            modifier = Modifier
                .constrainAs(birthDateTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(top = 20.dp, start = 20.dp),
            color = Constants.Dark_Blue,
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        DatePickerDialogScreen(
            appViewModel = appViewModel,
            modifier = Modifier
                .constrainAs(birthDateTF) {
                    top.linkTo(birthDateTitle.bottom)
                }
                .fillMaxWidth()
                .padding(top = 8.dp, start = 12.dp, end = 12.dp),
            errorMessage = "User must be at least 9",
            isSelectionOfDatesAvailableReversed =true,
            insertedDate = {},
            isTextFieldValid = mutableStateOf(true)
        )



        GenericOutlinedTextField(
            tint = Light_Blue,
            value = newPhoneNumber,
            thisValueChange = {
                newPhoneNumber = it
                appViewModel.updatePhoneNumber(newPhoneNumber)
            },
            label = newPhoneNumberMessage,
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(newPhoneNumberTF) {
                    top.linkTo(birthDateTF.bottom)
                },
            imageVector = Icons.Default.Smartphone,
            isError = isNewPhoneNumberError,
            visualTransformation = VisualTransformation.None,
            trailingImageVector = Icons.Default.Transform,
        )
    }
}

