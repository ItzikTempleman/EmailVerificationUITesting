package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.itzik.user_with_testing.project.ui.shapes.Blue

@Composable
fun GenericOutlinedTextField(
    data: String? = null,
    isTrailingIconExist: Boolean,
    value: String,
    thisValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    imageVector: ImageVector,
    isError: Boolean,
    isKeyboardPasswordType: Boolean,
    isIconClickable: Boolean,
    visualTransformation: VisualTransformation,
    isPasswordIconShowing: ((Boolean) -> Unit)? = null,
    isPasswordToggleClicked: Boolean? = null,
) {
    val isIconClickableValue by remember {
        mutableStateOf(isIconClickable)
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
                    tint = Blue
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
                        tint = Blue
                    )
                }
            }
        },
        singleLine = true,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Blue,
            unfocusedBorderColor = Color.DarkGray,
            backgroundColor = Color.White
        ),
        isError = isError,
        keyboardOptions = if (isKeyboardPasswordType) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}
