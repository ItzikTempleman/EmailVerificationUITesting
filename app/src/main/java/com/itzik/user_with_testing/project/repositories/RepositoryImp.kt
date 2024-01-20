package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.data.UserDao
import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.models.flight_models.FlightInfoResponse
import com.itzik.user_with_testing.project.requests.FlightService
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

class RepositoryImp @Inject constructor(
    @Singleton
    private val userDao: UserDao,

    @Singleton
    @Named("Flights") private val flightService: FlightService,

    ) : IRepository {
    private val userList: MutableList<User> = mutableListOf()

    init {
        refreshUserList()
    }

    private fun refreshUserList() {
        runBlocking {
            userList.clear()
            userList.addAll(userDao.getAllUsers())
        }
    }


    override suspend fun getAllUsers(): List<User> = userDao.getAllUsers()


    override suspend fun saveUser(user: User) {
        userDao.saveUser(user)
        refreshUserList()
    }

    override suspend fun getSignedInUser(): User? = userList.find { it.isSignedIn }






















    override suspend fun getUserFromUserNameAndPassword(userName: String, password: String): User =
        userDao.getUserFromUserNameAndPassword(userName, password)


    override suspend fun getFlightInfo(
        sourceAirportCode: String,
        destinationAirportCode: String,
        takeoffDate: String,
        itineraryType: String,
        numberOfAdults: Int,
        classOfService: String,
        currency: String,
        returnDate: String,
    ): Response<FlightInfoResponse> = flightService.getFlight(
        sourceAirportCode,
        destinationAirportCode,
        takeoffDate,
        itineraryType,
        numberOfAdults,
        classOfService,
        currency,
        returnDate
    )


    override suspend fun getAirportCodeName(query: String): Response<AirportCodeName> =
        flightService.getAirportCodeName(query)

}