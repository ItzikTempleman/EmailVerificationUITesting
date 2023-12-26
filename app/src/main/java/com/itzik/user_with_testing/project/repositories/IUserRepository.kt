package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.models.User

interface IUserRepository {
    suspend fun getUsers(): List<User>

    suspend fun saveUser(user: User)
}