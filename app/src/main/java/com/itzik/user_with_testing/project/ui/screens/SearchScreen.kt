package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
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
import com.itzik.user_with_testing.project.utils.Constants.Light_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun SearchScreen(
    modifier: Modifier,
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {


    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        var isDropdownMenuVisible by remember {
            mutableStateOf(false)
        }

        var searchDeparture by remember {
            mutableStateOf("")
        }
        var searchDestination by remember {
            mutableStateOf("")
        }
        val airportCodeNameList = remember {
            mutableStateOf(emptyList<String>())
        }

        val (title, searchRow, searchButton, dropDownMenu, resultsLazyColumn) = createRefs()
        Text(
            text = stringResource(id = R.string.find_flights),
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(8.dp),
            color = Dark_Blue,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth().height(50.dp)
                .constrainAs(searchRow) {
                    top.linkTo(title.bottom)
                }
        ) {
            TextField(
                modifier = Modifier
                    .width(205.dp),
                value = searchDeparture,
                onValueChange = {
                    searchDeparture = it
                    isDropdownMenuVisible = searchDeparture.length >= 5
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_departure_city),
                        color = Light_Blue
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.FlightTakeoff,
                        contentDescription = null,
                        tint = Light_Blue
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Transparent,
                    cursorColor = Light_Blue,
                    focusedIndicatorColor = Light_Blue,
                    unfocusedIndicatorColor = DarkGray.copy(0.3f),

                    ),
                singleLine = true
            )


            TextField(
                modifier = Modifier
                    .width(205.dp),
                value = searchDestination,
                onValueChange = {
                    searchDestination = it
                    isDropdownMenuVisible = searchDestination.length >= 5
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_destination_city),
                        color = Light_Blue
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.FlightLand,
                        contentDescription = null,
                        tint = Light_Blue
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Light_Blue,
                    backgroundColor = Transparent,
                    focusedIndicatorColor = Light_Blue,
                    unfocusedIndicatorColor = DarkGray.copy(0.3f)
                ), singleLine = true
            )
        }

        DropdownMenu(
            expanded = isDropdownMenuVisible,
            onDismissRequest = { isDropdownMenuVisible = false },
            modifier = Modifier
                .constrainAs(dropDownMenu) {
                    top.linkTo(searchRow.bottom)
                }
                .fillMaxWidth().height(200.dp)
        ) {
            LaunchedEffect(Unit) {
                val data = getCodeName(searchDeparture, appViewModel)
                airportCodeNameList.value = data
            }
            val updatedList = airportCodeNameList.value
            updatedList.forEach { item ->
                DropdownMenuItem(onClick = {
                    val regex = Regex("\\(([^)]+)\\)")
                    val matchResult = regex.find(item)
                    val codeName=matchResult?.groups?.get(1)?.value
                    if (codeName != null) {
                        searchDeparture = codeName
                    }
                    isDropdownMenuVisible = false
                }
                ) {
                    Text(text = item)
                }
            }
        }




        GenericButton(
            modifier = Modifier
                .constrainAs(searchButton) {
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {

            },
            buttonColor = Dark_Blue,
            text = "Search flights",
            textColor = White,
            roundedRadius = 12.dp
        )
    }
}


suspend fun getCodeName(
    searchCityQuery: String,
    appViewModel: AppViewModel,
): MutableList<String> {
    val airportCodeName = mutableListOf<String>()
    appViewModel.getAirportCodeName(searchCityQuery)
        .collect {
            it.data.forEach { singleAirportCode ->
                airportCodeName.add(singleAirportCode.shortName)
            }
        }
    return airportCodeName
}


