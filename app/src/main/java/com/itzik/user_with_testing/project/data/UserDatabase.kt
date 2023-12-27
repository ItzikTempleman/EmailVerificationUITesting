package com.itzik.user_with_testing.project.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.utils.UserConverter

@Database(entities = [User::class], version = 1)
@TypeConverters(UserConverter::class)
abstract class UserDatabase :RoomDatabase(){
    abstract fun getDao() :UserDao
}