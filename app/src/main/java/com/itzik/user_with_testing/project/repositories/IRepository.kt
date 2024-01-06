package com.itzik.user_with_testing.project.repositories

import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.models.flight_model.FlightResponse
import retrofit2.Response

interface IRepository {
    suspend fun getUsers(): List<User>

    suspend fun saveUser(user: User)

    suspend fun updateIsSignedIn(user: User)

    suspend fun getUserFromUserNameAndPassword(userName: String, password: String): User

    suspend fun getFlight(
        sourceAirportCode: String,
        destinationAirportCode: String,
        takeoffDate: String,
        roundOrDirect: String,
        numberOfAdults: Int,
        classOfService: String,
        currency: String,
        returnDate: String,
    ):Response<FlightResponse>

    suspend fun getAirportCodeName(
        query:String
    ):Response<AirportCodeName>
}