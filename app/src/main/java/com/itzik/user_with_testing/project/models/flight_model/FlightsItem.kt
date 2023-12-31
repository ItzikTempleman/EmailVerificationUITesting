package com.itzik.user_with_testing.project.models.flight_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightsItem(
    val segments: List<SegmentsItem>,
    val purchaseLinks: List<PurchaseLinksItem>,
) : Parcelable