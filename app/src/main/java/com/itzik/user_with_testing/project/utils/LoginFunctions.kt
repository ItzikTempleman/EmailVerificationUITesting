package com.itzik.user_with_testing.project.utils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.project.models.Gender
import com.itzik.user_with_testing.project.navigation.HomeGraph

fun isEmailValid(email: String): Boolean =
    email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())


fun isPasswordValid(password: String): Boolean = password.isNotBlank() && password.length > 9


fun loginMessage(context: Context, isSuccessfulData: Boolean) {
    Toast.makeText(context, if (isSuccessfulData) "Successfully logged in" else "Incorrect data, please fix", Toast.LENGTH_SHORT).show()
}



fun isUsernameValid(fullName: String): Boolean {
return false
}

fun isNewEmailValid(createEmail: String): Boolean {
    return false
}

fun isNewPasswordValid(createPassword: String): Boolean {
    return false
}

fun isGenderValid(genders: List<Gender>): Boolean {
    return false
}

fun isAgeValid(age: Int): Boolean {
    return false
}

fun isNewPhoneNumValid(newPhoneNumber: String): Boolean {
    return false
}




fun moveToHomeScreen(isSuccessfulData: Boolean, navHostController: NavHostController) {
    if (isSuccessfulData) {
        navHostController.navigate(HomeGraph.HomePage.route)
    }
}
