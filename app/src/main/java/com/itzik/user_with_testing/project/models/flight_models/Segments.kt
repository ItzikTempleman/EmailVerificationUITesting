package com.itzik.user_with_testing.project.models.flight_models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Segments(
	val legs: List<Legs>,
	val layovers: List<String>
) : Parcelable