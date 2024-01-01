package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen (
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    modifier: Modifier,
    userViewModel: UserViewModel,
){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        modifier
            .fillMaxSize()
            .background(Color.Cyan)
    }
}