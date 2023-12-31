package com.itzik.user_with_testing.project.utils

import com.itzik.user_with_testing.project.models.User

object Constants  {
    const val USER_TABLE="userTable"
    const val USER_DATABASE="userDatabase"

    const val BASE_URL = "https://tripadvisor16.p.rapidapi.com/"



    const val API_KEY_NAME = "x-rapidapi-key"
    const val API_KEY_VALUE = "1efcdfbbe9mshb72149862079f25p13b0afjsne6cce1f4c14d"
    const val API_HOST_NAME = "x-rapidapi-host"
    const val API_HOST_VALUE = "tripadvisor16.p.rapidapi.com"
}

fun mockEmptyUser(): User = User(0, listOf("", ""),"",0, "","", "", "", ""  )
