package com.itzik.user_with_testing.project.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.utils.Constants.USER_TABLE
import com.itzik.user_with_testing.project.utils.UserConverter

@Dao
@TypeConverters(UserConverter::class)
interface UserDao {
    @Query("SELECT*FROM $USER_TABLE ")
    suspend fun getUsers(): List<User>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user:User)

}