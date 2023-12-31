package com.itzik.user_with_testing.project.models.flight_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
	val numOfFilters: Int,
	val session: Session,
	val flights: List<FlightsItem>,
	val totalNumResults: Int
) : Parcelable