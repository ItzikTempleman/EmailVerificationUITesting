package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun GenericButton(
    modifier: Modifier,
    onClick: () -> Unit,
    buttonColor: Color,
    text: String,
    textColor:Color,
    roundedRadius: Dp,

) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(roundedRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = textColor
        )
    }
}