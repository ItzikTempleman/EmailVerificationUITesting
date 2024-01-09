package com.itzik.user_with_testing.project.models.flight_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
	val isDifferentDestinationStation: Boolean,
) : Parcelable