package com.itzik.user_with_testing.project.utils

import android.content.Context
import android.widget.Toast

fun isEmailValid(email: String): Boolean =
    email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())


fun isPasswordValid(password: String): Boolean = password.isNotBlank() && password.length > 9


fun loginMessage(context: Context, isSuccessfulData: Boolean) {
    Toast.makeText(
        context,
        if (isSuccessfulData) "Successfully logged in" else "Incorrect data, please fix",
        Toast.LENGTH_SHORT
    ).show()
}
