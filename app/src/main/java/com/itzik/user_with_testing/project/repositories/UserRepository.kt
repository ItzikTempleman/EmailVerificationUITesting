package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.models.User

interface UserRepository {
    suspend fun getUsers(): MutableList<User>

    suspend fun saveUser(user: User)
}