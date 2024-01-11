package com.itzik.user_with_testing.project.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    modifier: Modifier,
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {


    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (text, searchRow, button) = createRefs()

        var isDepartureExpanded by remember { mutableStateOf(false) }
        var isDestinationExpanded by remember { mutableStateOf(false) }

        val departureIcon = if (isDepartureExpanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown

        val desinationIcon = if (isDestinationExpanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown


        var searchDeparture by remember {
            mutableStateOf("")
        }

        var searchDestination by remember {
            mutableStateOf("")
        }

        val list = remember {
            mutableStateOf(emptyList<String>())
        }

        Text(
            text = stringResource(R.string.find_flights),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            color = Dark_Blue,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .constrainAs(searchRow) {
                    top.linkTo(text.bottom)
                }
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .width(205.dp)
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = searchDeparture,
                    onValueChange = { searchDeparture = it },
                    modifier = Modifier,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search_departure_city),
                            color = Dark_Blue,
                            fontSize = 14.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.FlightTakeoff,
                            contentDescription = null,
                            tint = Dark_Blue
                        )
                    },
                    trailingIcon = {
                        Icon(departureIcon, null,
                            Modifier.clickable { isDepartureExpanded = !isDepartureExpanded })
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Dark_Blue,
                        focusedIndicatorColor = Dark_Blue,
                        unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
                    ),
                    singleLine = true

                )
                DropdownMenu(
                    expanded = isDepartureExpanded,
                    onDismissRequest = { isDepartureExpanded = false },
                    modifier = Modifier
                ) {
                    LaunchedEffect(Unit) {
                        val data = appViewModel.getCodeName(searchDeparture)
                        list.value = data
                    }
                    val updatedList = list.value
                    updatedList.forEach { item ->
                        DropdownMenuItem(onClick = {
                            val regex = Regex("\\(([^)]+)\\)")
                            val matchResult = regex.find(item)
                            val codeName = matchResult?.groups?.get(1)?.value

                            if (codeName != null) {
                                searchDeparture = codeName
                            }
                            isDepartureExpanded = false
                        }) {
                            Text(text = item)
                        }
                    }
                }

            }
            Column(
                modifier = Modifier
                    .width(205.dp)
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = searchDestination,
                    onValueChange = { searchDestination = it },
                    modifier = Modifier,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search_destination_city),
                            color = Dark_Blue,
                            fontSize = 14.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.FlightLand,
                            contentDescription = null,
                            tint = Dark_Blue
                        )
                    },
                    trailingIcon = {
                        Icon(desinationIcon, null,
                            Modifier.clickable { isDestinationExpanded = !isDestinationExpanded })
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Dark_Blue,
                        focusedIndicatorColor = Dark_Blue,
                        unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
                    ),
                    singleLine = true
                )
                DropdownMenu(
                    expanded = isDestinationExpanded,
                    onDismissRequest = { isDestinationExpanded = false },
                    modifier = Modifier
                ) {
                    LaunchedEffect(Unit) {
                        val data = appViewModel.getCodeName(searchDestination)
                        list.value = data
                    }
                    val updatedDestinationList = list.value
                    updatedDestinationList.forEach { destinationItem ->
                        DropdownMenuItem(onClick = {
                            val regex = Regex("\\(([^)]+)\\)")
                            val matchResult = regex.find(destinationItem)
                            val codeName = matchResult?.groups?.get(1)?.value
                            if (codeName != null) {
                                searchDestination = codeName
                            }
                            isDestinationExpanded = false
                        }) {
                            Text(text = destinationItem)
                        }
                    }
                }
            }
        }




        GenericButton(
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                coroutineScope.launch {
                    appViewModel.getFlightInfo(
                        sourceAirportCode = searchDeparture,
                        destinationAirportCode = searchDestination,
                        takeoffDate = "2024-01-12",
                        roundOrDirect = "ROUND_TRIP",
                        numberOfAdults = 1,
                        classOfService = "ECONOMY",
                        currency = "USD",
                        returnDate = "2024-01-21"
                    ).collect {
                        val flightInfo = it
                        Log.d("TAG", "flightInfo: $flightInfo")
                    }
                }
            },
            buttonColor = Dark_Blue,
            text = "Search flights",
            textColor = White,
            roundedRadius = 12.dp
        )
    }
}