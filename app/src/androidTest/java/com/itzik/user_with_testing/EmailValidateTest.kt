package com.itzik.user_with_testing

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.itzik.user_with_testing.project.ui.screens.LoginScreen
import com.itzik.user_with_testing.theme.Main
import org.junit.Rule
import org.junit.Test

class EmailValidateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun validateEmail() {
        composeTestRule.setContent {
            Main {
                LoginScreen(
                    modifier = Modifier,
                    navHostController = rememberNavController()
                )

            }
        }
        composeTestRule.onNodeWithTag("emailTextField").performTextInput("itzik.templeman@gmail.com")
        composeTestRule.onNodeWithTag("passwordTextField").performTextInput("Qwerty1024")
        composeTestRule.onNodeWithTag("validationButton").performClick()

    }
}
