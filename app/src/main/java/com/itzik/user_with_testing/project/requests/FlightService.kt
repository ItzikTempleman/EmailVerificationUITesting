package com.itzik.user_with_testing.project.requests

import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.flight_models.FlightInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightService {

    @GET("api/v1/flights/searchAirport?")
    suspend fun getAirportCodeName(@Query("query") query: String): Response<AirportCodeName>


    @GET("api/v1/flights/searchFlights?")
    suspend fun getFlight(
        @Query("sourceAirportCode") sourceAirportCode: String,
        @Query("destinationAirportCode") destinationAirportCode: String,
        @Query("date") date: String,
        @Query("itineraryType") itineraryType: String,
        @Query("numAdults") numAdults: Int,
        @Query("classOfService") classOfService: String,
        @Query("currencyCode") currencyCode: String,
        @Query("returnDate") returnDate: String,
    ): Response<FlightInfoResponse>

}