package com.itzik.user_with_testing.project.models

data class User(
    val firstName: String,
    val familyName: String,
    val age: Int,
    val gender: Gender,
    val emailOrUserName: String,
    val password: String,
    val phoneNumber:String
)

sealed class Gender {
    object MALE : Gender()
    object FEMALE : Gender()
    object OTHER : Gender()
}