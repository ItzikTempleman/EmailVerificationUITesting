package com.itzik.user_with_testing.project.models.flight_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
	val searchId: String
) : Parcelable