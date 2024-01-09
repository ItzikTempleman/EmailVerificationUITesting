package com.itzik.user_with_testing.project.models.flight_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
	val session: Session,
	val complete: Boolean,
	val flights: List<FlightsItem>,
	val totalNumResults: Int
) : Parcelable