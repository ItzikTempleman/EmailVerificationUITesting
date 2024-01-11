package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itzik.user_with_testing.project.utils.Constants
import com.itzik.user_with_testing.project.viewmodels.AppViewModel

@Composable
fun DropDownMenuScreen(
    modifier: Modifier,
    searchParam: MutableState<String>,
    appViewModel: AppViewModel,
    isExpanded: MutableState<Boolean>,
    list: MutableState<List<String>>,
) {
    Column(
        modifier = modifier.width(200.dp)
    ) {
        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = {
                isExpanded.value = false
            },
            modifier = Modifier.background(Color.LightGray),
        ) {
            LaunchedEffect(Unit) {
                val data = appViewModel.getCodeName(searchParam.value)
                list.value = data
            }
            val updatedList = list.value

            updatedList.forEach { item ->
                DropdownMenuItem(onClick = {
                    val regex = Regex("\\(([^)]+)\\)")
                    val matchResult = regex.find(item)
                    val codeName = matchResult?.groups?.get(1)?.value

                    if (codeName != null) {
                        searchParam.value = codeName
                    }
                    isExpanded.value = false
                }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}




@Composable
fun TextFieldScreen(
    value: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    iconImage: ImageVector,
    label: String,
    isExpanded: MutableState<Boolean>,

    ) {
    val icon = if (isExpanded.value) {
        Icons.Default.KeyboardArrowUp
    } else {
        Icons.Default.KeyboardArrowDown
    }

    TextField(
        modifier = modifier.width(200.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = label,
                color = Constants.Dark_Blue,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = iconImage,
                contentDescription = null,
                tint = Constants.Dark_Blue
            )
        },
        trailingIcon = {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.clickable {
                isExpanded.value = !isExpanded.value
            })
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Constants.Dark_Blue,
            focusedIndicatorColor = Constants.Dark_Blue,
            unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
        ),
        singleLine = true
    )
}
////        }
////            TextFieldScreen(
////                value = searchDeparture,
////                modifier = Modifier,
////                onValueChange = {
////                    searchDeparture = it
////                },
////                iconImage = Icons.Default.FlightTakeoff,
////                label = stringResource(id = R.string.search_departure_city),
////                isExpanded = mutableStateOf(isDropdownDepartureMenuVisible)
////            )
////
////            TextFieldScreen(
////                value = searchDestination,
////                modifier = Modifier,
////                onValueChange = {
////                    searchDestination = it
////                },
////                iconImage = Icons.Default.FlightLand,
////                label = stringResource(id = R.string.search_destination_city),
////                isExpanded = mutableStateOf(isDropdownDestinationMenuVisible)
////            )
////        }
////
////        DropDownMenuScreen(
////            modifier = modifier.constrainAs(dropDownDepartureListColumn) {
////                top.linkTo(searchRow.bottom)
////                start.linkTo(parent.start)
////            },
////            searchParam = mutableStateOf(searchDeparture),
////            appViewModel = appViewModel,
////            isExpanded = mutableStateOf(isDropdownDepartureMenuVisible),
////            list = airportCodeNameList
////        )
////
////        DropDownMenuScreen(
////            modifier = modifier.constrainAs(dropDownLandingListColumn) {
////                top.linkTo(searchRow.bottom)
////                end.linkTo(parent.end)
////            },
////            searchParam = mutableStateOf(searchDestination),
////            appViewModel = appViewModel,
////            isExpanded = mutableStateOf(isDropdownDestinationMenuVisible),
////            list = airportCodeNameList
////        )
