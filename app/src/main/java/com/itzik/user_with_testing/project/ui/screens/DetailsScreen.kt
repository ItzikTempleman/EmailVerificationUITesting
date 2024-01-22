package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.project.models.flight_models.FlightInfoResponse
import com.itzik.user_with_testing.project.utils.Constants
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun DetailsScreen(
    result: FlightInfoResponse?=null,
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, flightLazyColumn) = createRefs()
        var airportNameDeparture=appViewModel.departureAirport
        var airportNameDestination=appViewModel.destinationAirport
        Text(
            modifier = Modifier.constrainAs(title) {
              start.linkTo(parent.start)
                top.linkTo(parent.top)
            }.padding(8.dp),
            fontSize = 18.sp,
            color = Constants.Dark_Blue,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            text = "Select a flight"
        )

    }
}