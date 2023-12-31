package com.itzik.user_with_testing.project.modules

import android.content.Context
import androidx.room.Room
import com.itzik.user_with_testing.project.data.UserDao
import com.itzik.user_with_testing.project.data.UserDatabase
import com.itzik.user_with_testing.project.repositories.IUserRepository
import com.itzik.user_with_testing.project.repositories.UserRepositoryImp
import com.itzik.user_with_testing.project.requests.FlightService
import com.itzik.user_with_testing.project.utils.Constants.BASE_URL
import com.itzik.user_with_testing.project.utils.Constants.USER_DATABASE
import com.itzik.user_with_testing.project.utils.FlightInterceptor
import com.itzik.user_with_testing.project.utils.UserConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserConverter(): UserConverter = UserConverter()


    @Provides
    @Singleton
    fun provideUserRepository(repositoryImpl: UserRepositoryImp): IUserRepository = repositoryImpl


    @Singleton
    @Provides
    fun provideDao(userDatabase: UserDatabase): UserDao = userDatabase.getDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(applicationContext, UserDatabase::class.java, USER_DATABASE)
            .addTypeConverter(UserConverter()).fallbackToDestructiveMigration().build()




    @Provides
    @Singleton
    fun provideRetrofitService(): FlightService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        FlightInterceptor()
                    ).build()
            )
            .build()
        return retrofit.create(FlightService::class.java)
    }


}