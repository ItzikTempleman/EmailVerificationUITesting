package com.itzik.user_with_testing.project.models.airport_model

data class SearchAirportResponse(
    val message:String,
    val data: List<AirportData>
)