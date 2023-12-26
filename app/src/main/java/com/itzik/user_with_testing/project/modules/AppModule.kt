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
class AppModule {

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): IUserRepository = UserRepositoryImp(userDao)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, USER_DATABASE)
            .addTypeConverter(UserConverter::class.java).allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(userDatabase: UserDatabase): UserDao = userDatabase.getDao()

}