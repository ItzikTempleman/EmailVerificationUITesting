package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.models.User

interface IUserRepository {
    suspend fun getUsers(): List<User>

    suspend fun saveUser(user: User)

    suspend fun updateIsSignedIn(user:User)

    suspend fun getUserFromUserNameAndPassword(userName:String, password:String):User
}