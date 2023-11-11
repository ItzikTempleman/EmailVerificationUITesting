package com.itzik.user_with_testing.project.ui.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.unit.dp


val Blue = Color(0xFF08648C)
val Yellow= Color(0xFFFFD557)

@Composable
fun RoundedBackGround(color:Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(
                clip = true
            )
            .background(
                Color.White
            )
    ) {}
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = -650f, top = -1800f) {
            drawCircle(color, radius = 550.dp.toPx())
        }
    }
}


























