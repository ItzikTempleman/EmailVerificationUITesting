package com.itzik.user_with_testing.project.modules

import android.content.Context
import androidx.room.Room
import com.itzik.user_with_testing.project.data.UserDao
import com.itzik.user_with_testing.project.data.UserDatabase
import com.itzik.user_with_testing.project.repositories.UserRepository
import com.itzik.user_with_testing.project.repositories.UserRepositoryImp
import com.itzik.user_with_testing.project.utils.Constants.USER_DATABASE
import com.itzik.user_with_testing.project.utils.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    private val typeConverter = Converters()

    @Provides
    @Singleton
    fun provideUserRepositoryImp(userDao: UserDao): UserRepository = UserRepositoryImp(userDao)


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDatabase::class.java, USER_DATABASE)
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(userDatabase: UserDatabase): UserDao = userDatabase.getDao()

}