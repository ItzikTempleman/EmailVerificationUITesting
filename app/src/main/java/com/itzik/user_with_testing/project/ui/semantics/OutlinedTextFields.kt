package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedTextFields(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    data: String,
    singleLine: Boolean = true,
    imageVector: ImageVector,
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier,
        label = {
            Text(
                text = label,
                fontSize = 20.sp
            )
        },
        trailingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
        },
        singleLine = singleLine
    )
}