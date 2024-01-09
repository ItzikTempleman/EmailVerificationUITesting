package com.itzik.user_with_testing.project.models.flight_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PurchaseLinksItem(
	val commerceName: String,
	val taxesAndFeesPerPassenger: Int,
	val totalPrice: Double,
	val taxesAndFees: Double,
	val totalPricePerPassenger: Double,
	val currency: String,
	val seatAvailability: Int,
	val originalCurrency: String
) : Parcelable