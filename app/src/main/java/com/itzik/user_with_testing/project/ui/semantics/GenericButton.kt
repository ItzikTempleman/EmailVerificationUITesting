package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun GenericButton(
    modifier: Modifier,
    onClick: () -> Unit,
    buttonColor: Color,
    text: String,
    textColor: Color,
    roundedRadius: Dp,
    imageVector: ImageVector? = null,
    buttonBorder:BorderStroke?=null,
    fontSize: TextUnit
) {
    Button(
        border = buttonBorder,
        modifier = modifier.height(50.dp),
        onClick = onClick,
        shape = RoundedCornerShape(roundedRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = White
        )
    ) {
        if (imageVector != null) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth().height(40.dp)
            ) {
                val (icon, title) = createRefs()

                Icon(
                    imageVector = imageVector,
                    modifier = Modifier
                        .size(30.dp)
                        .constrainAs(icon) {
                            start.linkTo(parent.start)

                        }.padding(top=8.dp),
                    contentDescription = null,
                    tint = White
                )
                Text(
                    modifier = Modifier
                        .constrainAs(title) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxHeight(),
                    text = text,
                    fontSize = 24.sp,
                    color = textColor,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Text(
                text = text,
                fontSize = fontSize,
                color = textColor,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }
    }
}