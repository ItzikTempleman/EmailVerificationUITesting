package com.itzik.user_with_testing.project.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.utils.Constants.USER_TABLE

@Dao
interface UserDao {
    @Query ("SELECT*FROM $USER_TABLE")
    suspend fun getUsers(): MutableList<User>

    @Insert
    suspend fun saveUser(user:User)

}