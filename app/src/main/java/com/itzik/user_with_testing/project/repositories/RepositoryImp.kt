package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.data.UserDao
import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.models.flight_models.FlightInfoResponse
import com.itzik.user_with_testing.project.requests.FlightService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class RepositoryImp @Inject constructor(@Singleton private val userDao: UserDao,@Singleton private val flightService: FlightService) : IRepository {

    override suspend fun getUsers(): List<User> = userDao.getUsers()

    override suspend fun saveUser(user: User) = userDao.saveUser(user)
    override suspend fun updateIsSignedIn(user: User) = userDao.updateIsSigneIn(user)
    override suspend fun getUserFromUserNameAndPassword(userName: String, password: String): User =
        userDao.getUserFromUserNameAndPassword(userName, password)

    override suspend fun getFlight(
        sourceAirportCode: String,
        destinationAirportCode: String,
        takeoffDate: String,
        roundOrDirect: String,
        numberOfAdults: Int,
        classOfService: String,
        currency: String,
        returnDate: String,
    ): Response<FlightInfoResponse> = flightService.getFlight(
        sourceAirportCode,
        destinationAirportCode,
        takeoffDate,
        roundOrDirect,
        numberOfAdults,
        classOfService,
        currency,
        returnDate
    )

    override suspend fun getAirportCodeName(query: String): Response<AirportCodeName> =
        flightService.getAirportCodeName(query)

}