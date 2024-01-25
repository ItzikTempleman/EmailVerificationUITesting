package com.itzik.user_with_testing.project.models.flight_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flights(
    val segments: List<Segments>,
    val purchaseLinks: List<PurchaseLinks>,
) : Parcelable