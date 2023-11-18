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



@Composable
fun RoundedBackGround(topColor:Color, bottomColor:Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(
                clip = true
            )
            .background(
                bottomColor
            )
    ) {}
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = 0f, top = -1800f) {
            drawCircle(topColor, radius = 700.dp.toPx())
        }
    }
}


























