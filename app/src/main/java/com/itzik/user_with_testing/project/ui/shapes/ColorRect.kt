package com.itzik.user_with_testing.project.ui.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

import com.itzik.user_with_testing.project.utils.Purple
import com.itzik.user_with_testing.project.utils.Yellow

@Composable
fun ColorRectangle() {

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

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .graphicsLayer(
                    shape = RoundedCornerShape(150.dp),
                    clip = true,
                    rotationX = 0f,
                    rotationY = 40f
                )
                .background(
                    Yellow
                )
        ) {}


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(800.dp)
                .graphicsLayer(
                    clip = true,
                    shape = RoundedCornerShape(150.dp),
                    rotationX = 40f,
                    rotationY = 0f
                )
                .background(
                    Purple
                )
        ) {}
    }
}
