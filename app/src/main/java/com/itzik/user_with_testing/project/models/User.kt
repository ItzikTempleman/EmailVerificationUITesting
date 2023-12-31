package com.itzik.user_with_testing.project.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.ui.graphics.vector.ImageVector

data class User(
    val firstName: String,
    val familyName: String,
    val age: Int,
    val gender: Gender,
    val emailOrUserName: String,
    val password: String,
    val phoneNumber:String
)

sealed class Gender(val name:String, val icon: ImageVector) {
    object MALE : Gender(name="Male", icon = Icons.Default.Male)
    object FEMALE : Gender(name="Female", icon = Icons.Default.Female)
    object OTHER : Gender(name="Other", icon = Icons.Default.Transgender)
}