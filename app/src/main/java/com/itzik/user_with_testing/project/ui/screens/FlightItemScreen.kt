package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.itzik.user_with_testing.project.models.flight_models.Flights
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue

@Preview
@Composable
fun FlightItemScreenPreview() {
    FlightItemScreen(modifier = Modifier,null,"New york", "Paris")
}

@Composable
fun FlightItemScreen(
    modifier: Modifier,
    flightItem: Flights?=null,
    departureAirportParamName:String,
    destinationAirportParamName:String
) {
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .width(330.dp)
                .height(180.dp)
        ) {
            val (originCity, arrowPointingRight, destinationCity, classOfService, numberOfSeats, totalPricePerTicket, operatingCarrierCode, numberOfStops, takeOffTime, landingTime) = createRefs()
            Text(
                modifier = Modifier
                    .constrainAs(originCity) {
                        start.linkTo(parent.start)
                        end.linkTo(arrowPointingRight.start)
                        top.linkTo(parent.top)
                    }
                    .padding(8.dp),
                fontSize = 18.sp,
                color = Dark_Blue,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                text = departureAirportParamName
            )

            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null,
                Modifier
                    .constrainAs(arrowPointingRight) {
                        start.linkTo(originCity.end)
                        end.linkTo(destinationCity.start)
                        top.linkTo(destinationCity.top)
                        bottom.linkTo(destinationCity.bottom)
                    }
                    .size(20.dp)
            )

            Text(
                modifier = Modifier
                    .constrainAs(destinationCity) {
                        end.linkTo(parent.end)
                        start.linkTo(arrowPointingRight.end)
                        top.linkTo(parent.top)
                    }
                    .padding(8.dp),
                fontSize = 18.sp,
                color = Dark_Blue,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                text = destinationAirportParamName
            )


        }
    }
}