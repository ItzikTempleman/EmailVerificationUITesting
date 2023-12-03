import android.app.DatePickerDialog
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePickerDialogScreen(
    modifier: Modifier,
) {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var isDatePickerVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(selectedDate.time),
        onValueChange = {},
        shape = RoundedCornerShape(6.dp),
        leadingIcon = {
            GenericRoundedButton(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Default.CalendarMonth,
                onClickFunction = {
                    isDatePickerVisible = true
                },
                tint = Dark_Green,
                innerIconColor = White
            )
        },
        textStyle = TextStyle(
            color = White,
            fontSize = 22.sp,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = White,
            unfocusedBorderColor = White,
            backgroundColor = Dark_Green
        ),
    )

    if (isDatePickerVisible) {
        val onDismissDateSelector = { isDatePickerVisible = false }
        val onDateSelected: (Long) -> Unit = { selectedDateInMillis ->
            selectedDate = Calendar.getInstance().apply {
                timeInMillis = selectedDateInMillis
            }
            onDismissDateSelector()
        }

        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, year, month, dayOfMonth ->
                val newDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                onDateSelected(newDate.timeInMillis)
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )

        DisposableEffect(LocalContext.current) {
            onDispose {
                datePickerDialog.dismiss()
            }
        }

        DisposableEffect(isDatePickerVisible) {
            if (isDatePickerVisible) {
                datePickerDialog.show()
            } else {
                datePickerDialog.dismiss()
            }

            onDispose {
                datePickerDialog.dismiss()
            }
        }
    }
}