package com.validation_testing.emailverificationuitesting

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import com.validation_testing.emailverificationuitesting.ui.LoginValidationScreen
import com.validation_testing.emailverificationuitesting.ui.theme.EmailVerificationUITestingTheme
import org.junit.Rule
import org.junit.Test

class EmailValidateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateEmail() {
        composeTestRule.setContent {
            EmailVerificationUITestingTheme {
                LoginValidationScreen(modifier = Modifier)
            }
        }
    }
}