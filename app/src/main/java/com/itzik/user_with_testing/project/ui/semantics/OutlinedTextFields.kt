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
import com.itzik.user_with_testing.project.navigation.Dark_Green


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
    trailingImageVector: ImageVector? = null,
    isError: Boolean? = null,
    isKeyboardPasswordType: Boolean,
    isIconClickable: Boolean,
    phoneNumberValue: String? = null,
    visualTransformation: VisualTransformation? = null,
    isPasswordIconShowing: ((Boolean) -> Unit)? = null,
    isPasswordToggleClicked: Boolean? = null,

    phoneNumberTFOuterLabel: ((String) -> Unit)? = null,
    phoneNumberTFInnerText: ((String) -> Unit)? = null,
) {


    val isIconClickableValue by remember {
        mutableStateOf(isIconClickable)
    }
    val isPasswordToggleClicked by remember {
        mutableStateOf(isPasswordToggleClicked)
    }
    if (isError != null) {
        if (visualTransformation != null) {
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
                        if (trailingImageVector != null) {
                            IconButton(onClick = {
                                if (phoneNumberTFInnerText != null) {
                                    if (phoneNumberTFOuterLabel != null) {
                                        phoneNumberTFOuterLabel("Reset text message sent")
                                        phoneNumberTFInnerText("Enter the code sent to your number")
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = trailingImageVector,
                                    contentDescription = null,
                                    tint = tint
                                )
                            }
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
    }
}


@Composable
fun DateOutlinedTextField(
    data: String? = null,
    value: String,
    thisValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation? = null,
    isError: Boolean? = null,

) {
    if (visualTransformation != null) {
        if (isError != null) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    thisValueChange(it)
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                },
                singleLine = true,
                visualTransformation = visualTransformation,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Dark_Green,
                    unfocusedBorderColor = Color.DarkGray,
                    backgroundColor = Color.White
                ),
                isError = isError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = if (label.contains("M") || label.contains("D")) {
                    2
                } else {
                    4
                }
            )
        }
    }
}