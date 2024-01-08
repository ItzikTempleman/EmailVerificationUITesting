package com.itzik.user_with_testing.project.requests

import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.flight_model.FlightResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightService {

    @GET("api/v1/flights/searchAirport?")
    suspend fun getAirportCodeName(@Query("query") query:String):Response<AirportCodeName>

    @GET(
        "api/v1/flights/searchFlights?" +
                "sourceAirportCode={takeOff}" +
                "&destinationAirportCode={landing}" +
                "&date={takeoffDate}" +
                "&itinerary={roundOrDirect}" +
                "&numAdults={numberOfAdults}" +
                "&classOfService={class}" +
                "&currencyCode={currency}" +
                "&returnDate={returnDate}"
    )
    suspend fun getFlight(
        @Query("takeOff") takeOffAirport: String,
        @Query("landing") landingAirport: String,
        @Query("takeoffDate") takeOffDate: String,
        @Query("itinerary") roundOrDirect: String,
        @Query("numAdults") numberOfAdults: Int,
        @Query("classOfService") classOfService: String,
        @Query("currency") currency: String,
        @Query("returnDate") returnDate: String,
    ): Response<FlightResponse>

}