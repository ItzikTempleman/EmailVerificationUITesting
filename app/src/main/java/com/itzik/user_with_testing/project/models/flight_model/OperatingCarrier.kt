package com.itzik.user_with_testing.project.models.flight_model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class OperatingCarrier(
	val code: String,
	val locationId: Int,
	val displayName: String,
	val logoUrl: String
) : Parcelable