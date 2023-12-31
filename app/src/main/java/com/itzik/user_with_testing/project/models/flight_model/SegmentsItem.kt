package com.itzik.user_with_testing.project.models.flight_model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SegmentsItem(
	val legs: List<LegsItem>
) : Parcelable