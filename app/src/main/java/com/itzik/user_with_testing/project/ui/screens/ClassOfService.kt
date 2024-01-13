package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlightClass
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.itzik.user_with_testing.project.utils.Constants.Bronze
import com.itzik.user_with_testing.project.utils.Constants.Gold
import com.itzik.user_with_testing.project.utils.Constants.Platinum
import com.itzik.user_with_testing.project.utils.Constants.Silver

sealed class ClassOfService(val name: String, val icon: ImageVector, val color: Color) {
    data object economy :
        ClassOfService(name = "ECONOMY", Icons.Default.FlightClass, color = Bronze)

    data object premiumEconomy :
        ClassOfService(name = "PREMIUM_ECONOMY", Icons.Default.FlightClass, color = Silver)

    data object business :
        ClassOfService(name = "BUSINESS", Icons.Default.FlightClass, color = Gold)

    data object firstClass :
        ClassOfService(name = "FIRST", Icons.Default.FlightClass, color = Platinum)
}
