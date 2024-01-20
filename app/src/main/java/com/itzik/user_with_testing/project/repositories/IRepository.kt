package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.models.flight_models.FlightInfoResponse
import retrofit2.Response

interface IRepository {
    suspend fun getUsers(): MutableList<User>

    suspend fun saveUser(user: User)

    suspend fun updateIsSignedIn(user: User)

    suspend fun getUserFromUserNameAndPassword(userName: String, password: String): User


    suspend fun getFlightInfo(
        sourceAirportCode: String,
        destinationAirportCode: String,
        takeoffDate: String,
        itineraryType: String,
        numberOfAdults: Int,
        classOfService: String,
        currency: String,
        returnDate: String,
    ): Response<FlightInfoResponse>


    suspend fun getAirportCodeName(
        query: String,
    ): Response<AirportCodeName>
}