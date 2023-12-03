package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun GenericRoundedButton(
    modifier: Modifier,
    imageVector: ImageVector,
    onClickFunction: ()-> Unit,
    tint:Color,
    innerIconColor:Color
    ) {
    OutlinedButton(
        border = BorderStroke(1.2.dp, tint),
        shape = CircleShape,
        colors= ButtonDefaults.outlinedButtonColors(backgroundColor = innerIconColor),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 4.dp
        ),
        onClick = {
            onClickFunction()
        },
        modifier = modifier
            .size(36.dp),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = tint
        )
    }
}

