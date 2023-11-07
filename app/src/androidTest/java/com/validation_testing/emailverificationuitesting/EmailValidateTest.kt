package com.validation_testing.emailverificationuitesting

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
        composeTestRule.onNodeWithTag("emailTextField").performTextInput("itzik.templeman@gmail.com")
        composeTestRule.onNodeWithTag("passwordTextField").performTextInput("Qwerty1024")
        composeTestRule.onNodeWithTag("validationButton").performClick()
    }
}