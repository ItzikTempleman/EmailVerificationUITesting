package com.itzik.user_with_testing.project.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.utils.Constants.USER_TABLE
import com.itzik.user_with_testing.project.utils.UserConverter

@Dao
@TypeConverters(UserConverter::class)
interface UserDao {

    @Insert
    suspend fun saveUser(user: User)

    @Query("SELECT*FROM $USER_TABLE WHERE isSignedIn=1")
    suspend fun getUsers(): MutableList<User>

    @Update
    suspend fun updateIsSigneIn(user: User)


    @Query("SELECT*FROM $USER_TABLE WHERE email = :userName AND password = :password")
    suspend fun getUserFromUserNameAndPassword(userName: String, password: String): User



}