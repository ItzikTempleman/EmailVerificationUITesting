package com.itzik.user_with_testing.project.requests

import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.flight_model.FlightResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FlightService {

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
        @Path("takeOff") takeOffAirport: String,
        @Path("landing") landingAirport: String,
        @Path("takeoffDate") takeOffDate: String,
        @Path("itinerary") roundOrDirect: String,
        @Path("numAdults") numberOfAdults: Int,
        @Path("classOfService") classOfService: String,
        @Path("currency") currency: String,
        @Path("returnDate") returnDate: String,
    ): Response<FlightResponse>


    @GET("api/v1/flights/searchAirport?")
    suspend fun getAirportCodeName(
        @Query("query") airportName:String
    ):Response<AirportCodeName>
}