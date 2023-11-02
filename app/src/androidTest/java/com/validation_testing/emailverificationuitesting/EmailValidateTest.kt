package com.validation_testing.emailverificationuitesting

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.validation_testing.emailverificationuitesting.ui.LoginValidationScreen
import com.validation_testing.emailverificationuitesting.ui.theme.EmailVerificationUITestingTheme
import org.junit.Rule
import org.junit.Test

class EmailValidateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateEmail(){
        composeTestRule.setContent {
            EmailVerificationUITestingTheme {
                LoginValidationScreen(modifier = Modifier)
                //composeTestRule.onNodeWithTag("button").performClick()
            }
        }



        //        repeat(2){
        //            composeTestRule.onNodeWithTag("button").performClick()
        //        }
    }
}