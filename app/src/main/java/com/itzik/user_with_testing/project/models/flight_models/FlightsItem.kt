package com.itzik.user_with_testing.project.models.flight_models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class FlightsItem(
	val segments: List<SegmentsItem>,
	val purchaseLinks: List<PurchaseLinksItem>
) : Parcelable