package com.itzik.user_with_testing

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

import com.itzik.user_with_testing.project.ui.screens.LoginScreen

import org.junit.Rule
import org.junit.Test

class EmailValidateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateEmail() {
        composeTestRule.setContent {
                LoginScreen(modifier = Modifier)
        }

        composeTestRule.onNodeWithTag("emailTextField").performTextInput("itzik.templeman@gmail.com")
        composeTestRule.onNodeWithTag("passwordTextField").performTextInput("Qwerty1024")
        composeTestRule.onNodeWithTag("validationButton").performClick()

    }
}
