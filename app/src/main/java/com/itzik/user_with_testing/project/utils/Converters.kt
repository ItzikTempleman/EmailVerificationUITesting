package com.itzik.user_with_testing.project.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itzik.user_with_testing.project.models.Gender
import com.itzik.user_with_testing.project.models.User


@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromUser(user: User): String = Gson().toJson(user)

    @TypeConverter
    fun toUser(user: String): User = Gson().fromJson(user, object : TypeToken<User>() {}.type)


    @TypeConverter
    fun fromGenderToString(gender: Gender): String = Gson().toJson(gender)

    @TypeConverter
    fun fromStringToGender(genderString: String) :Gender= Gson().fromJson(genderString, object : TypeToken<Gender>(){}.type)
}