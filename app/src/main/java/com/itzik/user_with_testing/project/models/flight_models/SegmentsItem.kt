package com.itzik.user_with_testing.project.models.flight_models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SegmentsItem(
	val layovers: List<String>,
	val legs: List<LegsItem>
) : Parcelable