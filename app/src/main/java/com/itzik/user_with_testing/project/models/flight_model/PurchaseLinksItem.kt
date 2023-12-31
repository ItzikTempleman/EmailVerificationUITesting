package com.itzik.user_with_testing.project.models.flight_model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PurchaseLinksItem(
	val commerceName: String? = null,
	val taxesAndFeesPerPassenger: Double,
	val totalPrice: Double,
	val taxesAndFees: Double,
	val totalPricePerPassenger: Double,
	val currency: String,
	val seatAvailability: Int? = null,
	val originalCurrency: String
) : Parcelable