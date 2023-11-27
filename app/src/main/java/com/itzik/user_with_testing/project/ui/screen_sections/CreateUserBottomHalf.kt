package com.itzik.user_with_testing.project.ui.screen_sections

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Transform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.Light_Purple
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.GenericOutlinedTextField
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import java.util.Date


@Composable
fun CreateUserBottomHalf(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
    context: Context,
) {
    var newPhoneNumber by remember { mutableStateOf("") }
    val newPhoneNumberText = stringResource(id = R.string.enter_new_phone_number)
    val newPhoneNumberMessage by remember { mutableStateOf(newPhoneNumberText) }
    val isNewPhoneNumberError by remember { mutableStateOf(false) }


    val selectDateTextValue = stringResource(id = R.string.birthdate)
    var selectBirthDate by remember { mutableStateOf(selectDateTextValue) }

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    val date = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )


    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (birthDateRow, newPhoneNumberTF, createUserBtn) = createRefs()


        OutlinedTextField(
            modifier = Modifier
                .constrainAs(birthDateRow) {
                    top.linkTo(parent.top)
                }
                .clickable {
                    datePickerDialog.show()
                }
                .fillMaxWidth()
                .padding(top = 30.dp, start = 12.dp, bottom = 12.dp, end = 12.dp),
            value = selectBirthDate,
            onValueChange = {
                selectBirthDate = it
            },
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = Dark_Green
                )
            },
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Dark_Green,
                unfocusedBorderColor = Color.DarkGray,
                backgroundColor = White
            ),
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

            },
            buttonColor = Light_Purple,
            text = stringResource(id = R.string.create_account),
            textColor = White,
            roundedRadius = 12.dp
        )
    }
}

