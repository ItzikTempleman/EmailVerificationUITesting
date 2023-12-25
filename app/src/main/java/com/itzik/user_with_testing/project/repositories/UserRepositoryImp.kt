package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.data.UserDao
import com.itzik.user_with_testing.project.models.User
import javax.inject.Inject
import javax.inject.Singleton

class UserRepositoryImp @Inject constructor(

    @Singleton
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUsers(): MutableList<User> = userDao.getUsers()

    override suspend fun saveUser(user: User) =userDao.saveUser(user)

}