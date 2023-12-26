package com.itzik.user_with_testing.project.modules

import android.content.Context
import androidx.room.Room
import com.itzik.user_with_testing.project.data.UserDao
import com.itzik.user_with_testing.project.data.UserDatabase
import com.itzik.user_with_testing.project.repositories.IUserRepository
import com.itzik.user_with_testing.project.repositories.UserRepositoryImp
import com.itzik.user_with_testing.project.utils.Constants.USER_DATABASE
import com.itzik.user_with_testing.project.utils.UserConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserConverter(): UserConverter = UserConverter()

//    @Singleton
//    @Provides
//    fun provideUserRepository(userDao: UserDao): IUserRepository = UserRepositoryImp(userDao)

    @Provides
    @Singleton
    fun provideUserRepository(repositoryImpl: UserRepositoryImp): IUserRepository = repositoryImpl

    
    @Singleton
    @Provides
    fun provideDao(userDatabase: UserDatabase): UserDao = userDatabase.getDao()
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDatabase::class.java, USER_DATABASE).build()

}