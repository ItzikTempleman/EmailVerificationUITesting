package com.itzik.user_with_testing.project.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.itzik.user_with_testing.project.navigation.AppNavGraph
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import com.itzik.user_with_testing.theme.EmailVerificationUITestingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            userViewModel = viewModel()
            val coroutineScope = rememberCoroutineScope()
            EmailVerificationUITestingTheme {
                AppNavGraph(userViewModel, coroutineScope, navController = rememberNavController())
            }
        }
    }
}

