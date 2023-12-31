package com.itzik.user_with_testing.project.models.flight_model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class LegsItem(
    val classOfService: String,
    val originStationCode: String,
    val numStops: Int,
    val flightNumber: Int,
    val distanceInKM: Double,
    val isDifferentOriginStation: Boolean,
    val operatingCarrierCode: String,
    val isInternational: Boolean,
    val departureDateTime: String,
    val destinationStationCode: String,
    val arrivalDateTime: String,
    val operatingCarrier: OperatingCarrier,
    val marketingCarrierCode: String
) : Parcelable