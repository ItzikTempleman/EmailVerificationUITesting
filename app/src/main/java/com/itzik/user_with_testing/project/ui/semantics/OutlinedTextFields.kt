package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.Light_Orange


@Composable
fun GenericOutlinedTextField(
    data: String? = null,
    tint: Color,
    isTrailingIconExist: Boolean,
    value: String,
    thisValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    imageVector: ImageVector,
    trailingImageVector: ImageVector,
    isError: Boolean,
    isKeyboardPasswordType: Boolean,
    isIconClickableParam: Boolean,
    phoneNumberValue: String? = null,
    visualTransformation: VisualTransformation,
    isPasswordIconShowing: ((Boolean) -> Unit)? = null,
    isPasswordToggleClicked: Boolean? = null,

    phoneNumberTFOuterLabel: (String) -> Unit,
) {


    val isIconClickableValue by remember {
        mutableStateOf(isIconClickableParam)
    }
    val isPasswordToggleClicked by remember {
        mutableStateOf(isPasswordToggleClicked)
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            thisValueChange(it)
        },
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                fontSize = 16.sp,
                color = Color.Black
            )
        },
        leadingIcon = {
            if (!isIconClickableValue) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = tint
                )
            } else {
                IconButton(onClick = {
                    if (isPasswordIconShowing != null) {
                        isPasswordToggleClicked?.let {
                            isPasswordIconShowing(it)
                        }
                    }
                }) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null,
                        tint = tint
                    )
                }
            }
        },
        trailingIcon = {
            if (isTrailingIconExist) {
                IconButton(onClick = {
                    phoneNumberTFOuterLabel("Enter reset code sent to you")
                }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null,
                        tint = Light_Orange
                    )
                }
            }

        },

        singleLine = true,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = tint,
            unfocusedBorderColor = Color.DarkGray,
            backgroundColor = Color.White
        ),

        isError = isError,
        keyboardOptions = if (isKeyboardPasswordType) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else KeyboardOptions(keyboardType = KeyboardType.Text)
    )


}

@Composable
fun DateOutlinedTextField(
    data: String? = null,
    value: String,
    thisValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation,
    isError: Boolean,
    modifier: Modifier,
) {


    OutlinedTextField(

        value = value,
        onValueChange = {
            if (label == "DD" || label == "MM") {
                if (it.length < 3) thisValueChange(it)
            } else {
                if (it.length < 5) thisValueChange(it)
            }
        },
        label = {
            Text(
                text = label,
                fontSize = 20.sp,
                color = Color.Black
            )
        },
        textStyle = MaterialTheme.typography.body1.copy(
            fontSize = 24.sp,
            color = Dark_Green,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        ),
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Dark_Green,
            unfocusedBorderColor = Color.DarkGray,
            backgroundColor = Color.White
        ),
        modifier = modifier
            .height(70.dp)
            .padding(2.dp),
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

    )
}