package com.itzik.user_with_testing.project.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itzik.user_with_testing.project.models.User


@ProvidedTypeConverter
class UserConverter {

    @TypeConverter
    fun fromStringList(firstNameAsList: List<String>): String = Gson().toJson(firstNameAsList)

    @TypeConverter
    fun toStringList(firstNameAsString: String): List<String> = Gson().fromJson(firstNameAsString, object : TypeToken<List<String>>() {}.type)


    @TypeConverter
    fun fromUser(user: User): String = Gson().toJson(user)

    @TypeConverter
    fun toUser(userString: String): User = Gson().fromJson(userString, User::class.java)

}