
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.utils.Constants.Light_Green
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePickerDialogScreen(
    modifier: Modifier,
    userViewModel: UserViewModel,
) {

    var isValidAge by remember { mutableStateOf(false) }
    val todayDate by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedDate by remember { mutableStateOf<Calendar?>(null) }
    var isDatePickerVisible by remember { mutableStateOf(false) }
    val datePickerDialog: DatePickerDialog?

    OutlinedTextField(
        value = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(
            if(selectedDate != null)
                selectedDate!!.time
            else todayDate.time),
        onValueChange = {},
        enabled = false,
        shape = RoundedCornerShape(6.dp),
        leadingIcon = {
            GenericRoundedButton(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Default.CalendarMonth,
                onClickFunction = {
                    isDatePickerVisible = true
                },
                outerTint = Light_Green,
                iconTint= Light_Green,
                innerIconColor = White,
                borderWidth = 1.dp
            )
        },
        textStyle = TextStyle(
            color = Black,
            fontSize = 16.sp,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        modifier = modifier.clickable {
          isDatePickerVisible = true
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Light_Green,
            unfocusedBorderColor = Black,
            backgroundColor = White
        ),
        isError = isValidAge,
        label = {
            if (!isValidAge && selectedDate != null) {
                Text(
                    text = "Must be at least 9 years old to sign up",
                    fontSize = 16.sp,
                    color = Color.Red
                )
            }
        }
    )

    if (isDatePickerVisible) {
        val onDismissDateSelector = { isDatePickerVisible = false }
        val onDateSelected: (Long) -> Unit = {
            selectedDate = Calendar.getInstance().apply {
                timeInMillis = it
            }
            isValidAge = userViewModel.validateAge(selectedDate ?: Calendar.getInstance())
            onDismissDateSelector()
        }

        datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, year, month, dayOfMonth ->
                val updatedDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                onDateSelected(updatedDate.timeInMillis)
            },
            todayDate.get(Calendar.YEAR),
            todayDate.get(Calendar.MONTH),
            todayDate.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
        datePickerDialog.setOnDismissListener {
            isDatePickerVisible = false
        }
    }
}