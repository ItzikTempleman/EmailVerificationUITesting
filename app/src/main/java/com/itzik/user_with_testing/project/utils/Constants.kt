package com.itzik.user_with_testing.project.utils

import androidx.compose.ui.graphics.Color
import com.itzik.user_with_testing.project.models.User

object Constants  {

    val Dark_Blue = Color(0xFF1c3456)
    val Light_Blue = Color(0xFF7db9be)

    const val USER_TABLE="userTable"
    const val USER_DATABASE="userDatabase"

    const val BASE_URL = "https://tripadvisor16.p.rapidapi.com/"
    const val API_KEY_NAME = "X-RapidAPI-Key"
    const val API_KEY_VALUE = "1efcdfbbe9mshb72149862079f25p13b0afjsne6cce1f4c14d"
    const val API_HOST_NAME = "X-RapidAPI-Host"
    const val API_HOST_VALUE = "tripadvisor16.p.rapidapi.com"
}

fun mockEmptyUser(): User = User(0, listOf("", ""),"",0, "","", "", "", ""  )
