
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Log
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
import androidx.compose.runtime.MutableState
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
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue
import com.itzik.user_with_testing.project.utils.Constants.Light_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePickerDialogScreen(
    modifier: Modifier,
    appViewModel: AppViewModel,
    errorMessage: String,
    isSelectionOfDatesAvailableReversed: Boolean,
    insertedDate : (String) -> Unit,
    isTextFieldValid: MutableState<Boolean>
) {

    var isValidEnteredDate by remember { mutableStateOf(false) }
    val todayDate by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedDate by remember { mutableStateOf<Calendar?>(null) }
    var isDatePickerVisible by remember { mutableStateOf(false) }
    val datePickerDialog: DatePickerDialog?

    OutlinedTextField(
        value = if(isTextFieldValid.value) {
            SimpleDateFormat("dd/MM/yyyy", Locale.US).format(
                if (selectedDate != null)
                    selectedDate!!.time
                else todayDate.time
            )
        } else {
              "XXXX"
        },
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
                outerTint = Dark_Blue,
                iconTint = Dark_Blue,
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
            focusedBorderColor = Light_Blue,
            unfocusedBorderColor = Black,
            backgroundColor = White
        ),
        isError = isValidEnteredDate,
        label = {
            if (!isValidEnteredDate && selectedDate != null) {
                Text(
                    text = errorMessage,
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
            insertedDate(parseDateToStringFormat(selectedDate).toString())
            Log.d("TAG", "selected date: ${parseDateToStringFormat(selectedDate)}")
            isValidEnteredDate = if (isSelectionOfDatesAvailableReversed) {
                appViewModel.validateAge(selectedDate ?: Calendar.getInstance())
            }else appViewModel.maxOutAtAYearAhead(selectedDate ?: Calendar.getInstance())
            onDismissDateSelector()

        }

        datePickerDialog = DatePickerDialog(
            LocalContext.current,
            R.style.DatePickerTheme,
            { _, year, month, dayOfMonth ->
                val updatedDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                onDateSelected(updatedDate.timeInMillis)
            },
            todayDate.get(Calendar.YEAR),
            todayDate.get(Calendar.MONTH),
            todayDate.get(Calendar.DAY_OF_MONTH),
        )
        if(isSelectionOfDatesAvailableReversed) datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        else datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
        datePickerDialog.setOnDismissListener {
            isDatePickerVisible = false
        }
    }
}

@SuppressLint("SimpleDateFormat", "SuspiciousIndentation")
fun parseDateToStringFormat(selectedDate: Calendar?): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return selectedDate?.time?.let { dateFormat.format(it) }
}

