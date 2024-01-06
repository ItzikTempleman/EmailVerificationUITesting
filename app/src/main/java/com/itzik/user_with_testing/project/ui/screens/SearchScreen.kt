package com.itzik.user_with_testing.project.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navController: NavHostController,
    appViewModel: AppViewModel,
    coroutineScope: CoroutineScope,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        var searchDepartureCityQuery by remember {
            mutableStateOf("")
        }
        var searchDestinationCityQuery by remember {
            mutableStateOf("")
        }
        val (searchRow, searchButton, resultsLazyColumn) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(searchRow) {
                    top.linkTo(parent.top)
                }
                .padding(8.dp)
        ) {
            TextField(
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 2.dp),
                value = searchDepartureCityQuery,
                onValueChange = {
                    searchDepartureCityQuery = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.search_departure_city))
                }
            )


            TextField(
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 2.dp),
                value = searchDepartureCityQuery,
                onValueChange = {
                    searchDepartureCityQuery = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.search_destination_city))
                }
            )
        }
        GenericButton(
            modifier = Modifier
                .constrainAs(searchButton) {
                    top.linkTo(searchRow.bottom)
                }
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                coroutineScope.launch {
                    appViewModel.getAirportCodeName(searchDepartureCityQuery)
                        .collect { departureAirportNameCode ->
                            Log.d("TAG", "Airport Codename: $departureAirportNameCode")
                        }
                    appViewModel.getAirportCodeName(searchDestinationCityQuery)
                        .collect { destinationAirportNameCode ->
                            Log.d("TAG", "Airport Codename: $destinationAirportNameCode")
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
