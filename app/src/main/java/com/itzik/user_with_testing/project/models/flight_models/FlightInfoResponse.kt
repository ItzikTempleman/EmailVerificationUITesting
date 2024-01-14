package com.itzik.user_with_testing.project.models.flight_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightInfoResponse(
    val data: Data,
    val message: String,
) : Parcelable

fun getEmptyResponse() = FlightInfoResponse(data = Data(flights = emptyList()), message = "")