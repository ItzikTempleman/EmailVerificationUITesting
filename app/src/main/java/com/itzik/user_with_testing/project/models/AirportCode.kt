package com.itzik.user_with_testing.project.models

data class AirportCodeName(
    val data:List<Data>
)

data class Data (
    val airportCode:String,
    val shortName:String
)
