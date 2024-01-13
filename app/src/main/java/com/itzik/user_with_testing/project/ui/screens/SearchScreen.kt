package com.itzik.user_with_testing.project.ui.screens

import DatePickerDialogScreen
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.RadioButtonChecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.ui.semantics.DropDownMenuScreen
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
        Image(
            painter = painterResource(id = R.drawable.wallpaper),
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight

        )
        var (text, searchRow, datesSelectionLayout, itineraryType, selectClass, button) = createRefs()

        val classOfServiceOptions = listOf(
            ClassOfService.economy,
            ClassOfService.premiumEconomy,
            ClassOfService.business,
            ClassOfService.firstClass
        )
        var defaultClass by remember {
            mutableStateOf(classOfServiceOptions.first())
        }

        var isChooseClassExpanded by remember { mutableStateOf(false) }
        var isDepartureExpanded by remember { mutableStateOf(false) }
        var isDestinationExpanded by remember { mutableStateOf(false) }

        val itineraryOptions = listOf(
            stringResource(id = R.string.round_trip),
            stringResource(id = R.string.one_way),
        )
        var defaultItineraryOption by remember { mutableStateOf(itineraryOptions[0]) }

        var isSelectNumberOfTicketExpanded by remember { mutableStateOf(false) }


        var searchDeparture by remember {
            mutableStateOf("")
        }

        var searchDestination by remember {
            mutableStateOf("")
        }

        val list = remember {
            mutableStateOf(emptyList<String>())
        }
        var takeOffDate by remember {
            mutableStateOf("")
        }
        var returnDate by remember {
            mutableStateOf("")
        }

        var selectedNumber by remember { mutableStateOf(1) }

        val expansionIcon = if (isChooseClassExpanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown

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

        Row(modifier = Modifier.constrainAs(searchRow) {
            top.linkTo(text.bottom)
        }
        ) {
            DropDownMenuScreen(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 2.dp),
                searchParam = mutableStateOf(searchDeparture),
                appViewModel = appViewModel,
                isExpanded = mutableStateOf(isDepartureExpanded),
                list = list,
                thisValueChange = {
                    searchDeparture = it
                },
                label = stringResource(id = R.string.search_departure_city),
                leadingIcon = Icons.Default.FlightTakeoff
            ) {
                searchDeparture = it
            }

            DropDownMenuScreen(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp, start = 2.dp),
                searchParam = mutableStateOf(searchDestination),
                appViewModel = appViewModel,
                isExpanded = mutableStateOf(isDestinationExpanded),
                list = list,
                thisValueChange = {
                    searchDestination = it
                },
                label = stringResource(id = R.string.search_destination_city),
                leadingIcon = Icons.Default.FlightLand
            ) {
                searchDestination = it
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .constrainAs(datesSelectionLayout) {
                    top.linkTo(searchRow.bottom)
                }
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            ConstraintLayout(modifier = Modifier) {

                val (textRow, datePickerRow) = createRefs()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(textRow) {
                            top.linkTo(parent.top)
                        }, horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {
                    Text(
                        text = stringResource(id = R.string.departure_date),
                        fontFamily = FontFamily.Default,
                        fontSize = 20.sp,
                        color = Dark_Blue
                    )

                    Text(
                        text = stringResource(id = R.string.return_date),
                        fontFamily = FontFamily.Default,
                        fontSize = 20.sp,
                        color = Dark_Blue
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(datePickerRow) {
                            top.linkTo(textRow.bottom)
                        }, horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {
                    DatePickerDialogScreen(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f),
                        appViewModel = appViewModel,
                        errorMessage = "",
                        isSelectionOfDatesAvailableReversed = false,
                        insertedDate = {
                            takeOffDate = it
                        }
                    )

                    DatePickerDialogScreen(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f),
                        appViewModel = appViewModel,
                        errorMessage = "",
                        isSelectionOfDatesAvailableReversed = false,
                        insertedDate = {
                            returnDate = it
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(itineraryType) {
                    top.linkTo(datesSelectionLayout.bottom)
                },
            horizontalArrangement = Arrangement.Center
        ) {
            itineraryOptions.forEach {
                Card(
                    modifier = Modifier.padding(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        RadioButton(colors = RadioButtonDefaults.colors(
                            selectedColor = Dark_Blue, unselectedColor = Dark_Blue
                        ), selected = (it == defaultItineraryOption), onClick = {
                            defaultItineraryOption = it

                        })
                        Text(
                            text = when (it) {
                                stringResource(id = R.string.one_way) -> "One way"
                                else -> "Round trip"
                            },
                            modifier = Modifier.padding(top = 10.dp, end = 8.dp),
                            fontSize = 16.sp,
                            color = Black
                        )

                    }
                }
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(imageVector = Icons.Default.AirplaneTicket, contentDescription = null)
                Icon(
                    modifier = Modifier.clickable {
                        isSelectNumberOfTicketExpanded = !isSelectNumberOfTicketExpanded
                    },
                    imageVector = if (isSelectNumberOfTicketExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null
                )
                Text(
                    text = selectedNumber.toString(), modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 20.sp
                )
                DropdownMenu(expanded = isSelectNumberOfTicketExpanded,
                    onDismissRequest = {
                        isSelectNumberOfTicketExpanded = false
                    }) {
                    for (i in 1..8) {
                        DropdownMenuItem(
                            modifier = Modifier
                                .width(40.dp)
                                .padding(end = 4.dp),
                            onClick = {
                                selectedNumber = i
                                isSelectNumberOfTicketExpanded = false
                            }
                        ) {
                            Text(
                                text = i.toString(),
                                color = Black
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(8.dp)

                .constrainAs(selectClass) {
                    top.linkTo(itineraryType.bottom)
                }
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = defaultClass.name + " CLASS",
                color = Dark_Blue,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
            Icon(imageVector = expansionIcon, contentDescription = null,
                modifier = Modifier.clickable {
                    isChooseClassExpanded = !isChooseClassExpanded
                })
            DropdownMenu(
                expanded = isChooseClassExpanded,
                onDismissRequest = {
                    isChooseClassExpanded = false
                }
            ) {
                classOfServiceOptions.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            defaultClass = item
                            isChooseClassExpanded = false
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.RadioButtonChecked,
                            contentDescription = null,
                            tint = item.color
                        )
                        Text(text = item.name)
                    }
                }
            }
        }

        GenericButton(modifier = Modifier
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
                        takeoffDate = takeOffDate,
                        itineraryType = defaultItineraryOption,
                        numberOfAdults = selectedNumber,
                        classOfService = defaultClass.name,
                        currency = "USD",
                        returnDate = returnDate
                    ).collect {
                        val flightInfo = it
                        Log.d("TAG", "flightInfo: $flightInfo")
                    }
                }
            },
            buttonColor = Dark_Blue,
            text = "Search flights",
            textColor = White,
            roundedRadius = 12.dp,
            fontSize=20.sp
        )
    }
}