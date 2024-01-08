package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itzik.user_with_testing.project.viewmodels.AppViewModel

@Composable
fun DropDownMenuScreen(
    modifier: Modifier,
    searchParam: MutableState<String>,
    appViewModel: AppViewModel,
    isDropdownMenuVisible: MutableState<Boolean>,
    airportCodeNameList: MutableState<List<String>>,
): String {


    Column(
        modifier = modifier.width(200.dp)
    ) {
        DropdownMenu(
            expanded = isDropdownMenuVisible.value,
            onDismissRequest = {
                isDropdownMenuVisible.value = false
            },
            modifier = Modifier.background(Color.LightGray),
        ) {
            LaunchedEffect(Unit) {
                val data = appViewModel.getCodeName(searchParam.value)
                airportCodeNameList.value = data
            }
            val updatedList = airportCodeNameList.value
            updatedList.forEach { item ->
                DropdownMenuItem(onClick = {
                    val regex = Regex("\\(([^)]+)\\)")
                    val matchResult = regex.find(item)
                    val codeName = matchResult?.groups?.get(1)?.value

                    if (codeName != null) {
                        searchParam.value = codeName
                    }
                    //Log.d("TAG", "Selected item: $selectedItem")
                    isDropdownMenuVisible.value = false
                }
                ) {
                    Text(text = item)
                }
            }
        }
    }
    return searchParam.value
}

