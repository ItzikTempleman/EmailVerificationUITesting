package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun SearchScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    coroutineScope: CoroutineScope
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.search))
    }
}
