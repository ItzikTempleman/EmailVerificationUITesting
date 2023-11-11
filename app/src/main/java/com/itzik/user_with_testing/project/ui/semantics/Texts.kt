package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun Texts(
    modifier: Modifier,
    text: String,
    fontSize: TextUnit,
    fontColor: Color,
    boldness: FontWeight,
    fontStyle: FontStyle
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize=fontSize,
        color =fontColor,
        fontWeight =boldness,
        fontStyle=fontStyle
    )
}