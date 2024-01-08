package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue


@Composable
fun TextFieldScreen(
    value:String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    iconImage: ImageVector,
    label: String
) {
    TextField(
        modifier = modifier.width(200.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = label,
                color = Dark_Blue,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = iconImage,
                contentDescription = null,
                tint = Dark_Blue
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Dark_Blue,
            focusedIndicatorColor = Dark_Blue,
            unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
        ),
        singleLine = true
    )
}