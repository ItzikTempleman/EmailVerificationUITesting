package com.validation_testing.emailverificationuitesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.validation_testing.emailverificationuitesting.ui.LoginValidationScreen
import com.validation_testing.emailverificationuitesting.ui.theme.EmailVerificationUITestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmailVerificationUITestingTheme {
                LoginValidationScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

