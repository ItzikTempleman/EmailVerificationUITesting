package com.itzik.user_with_testing.project.models.flight_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Layovers(
    val hasStationChanged: Boolean,
    val durationInMinutes: Int,
) : Parcelable
