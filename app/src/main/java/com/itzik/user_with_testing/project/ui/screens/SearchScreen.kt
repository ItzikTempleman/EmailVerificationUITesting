package com.itzik.user_with_testing.project.ui.screens

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.ui.semantics.DropDownMenuScreen
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.TextFieldScreen
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope

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
        val (text, searchRow, dropDownDepartureListColumn, dropDownLandingListColumn, button) = createRefs()

        var isDropdownDepartureMenuVisible by remember {
            mutableStateOf(false)
        }

        var isDropdownDestinationMenuVisible by remember {
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
                .height(50.dp)
        ) {
            TextFieldScreen(
                value =searchDeparture,
                modifier =Modifier,
                onValueChange = {
                    searchDeparture=it
                    isDropdownDepartureMenuVisible = searchDeparture.length == 5
                },
                iconImage = Icons.Default.FlightTakeoff,
                label =stringResource(id = R.string.search_departure_city)
            )

            TextFieldScreen(
                value =searchDestination,
                modifier =Modifier,
                onValueChange = {
                    searchDestination=it
                    isDropdownDestinationMenuVisible = searchDestination.length == 5
                },
                iconImage = Icons.Default.FlightLand,
                label =stringResource(id = R.string.search_destination_city)
            )
        }

        DropDownMenuScreen(
            modifier = modifier.constrainAs(dropDownDepartureListColumn) {
                    top.linkTo(searchRow.bottom)
                    start.linkTo(parent.start) },
            searchParam = mutableStateOf(searchDeparture),
            appViewModel = appViewModel,
            isDropdownMenuVisible = mutableStateOf(isDropdownDepartureMenuVisible),
            airportCodeNameList = airportCodeNameList
        )

        DropDownMenuScreen(
            modifier = modifier.constrainAs(dropDownLandingListColumn) {
                    top.linkTo(searchRow.bottom)
                    end.linkTo(parent.end) },
            searchParam = mutableStateOf(searchDestination),
            appViewModel = appViewModel,
            isDropdownMenuVisible = mutableStateOf(isDropdownDestinationMenuVisible),
            airportCodeNameList = airportCodeNameList
        )

        GenericButton(
            modifier = Modifier
                .constrainAs(button) {
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