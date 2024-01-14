package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun FlightResultScreen(
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Flight info"
        )
    }
}