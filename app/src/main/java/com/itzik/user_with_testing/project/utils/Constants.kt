package com.itzik.user_with_testing.project.utils

import com.itzik.user_with_testing.project.models.User

object Constants  {
    const val USER_TABLE="userTable"
    const val USER_DATABASE="userDatabase"
}

fun mockEmptyUser(): User = User(0, listOf("", ""),"",0, "","", "", "", ""  )
