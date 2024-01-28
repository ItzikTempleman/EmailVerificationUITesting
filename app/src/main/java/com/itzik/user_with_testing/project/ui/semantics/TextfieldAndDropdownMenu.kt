package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue
import com.itzik.user_with_testing.project.utils.Constants.Light_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel


@Composable
fun DropDownMenuScreen(
    modifier: Modifier,
    updateSearchWithCityName: (String) -> Unit,
    searchParam: MutableState<String>? = null,
    cityNameParam: MutableState<String>? = null,
    appViewModel: AppViewModel,
    isExpanded: MutableState<Boolean>,
    list: MutableState<List<String>>? = null,
    thisValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    updatedValue: (String) -> Unit,
) {


    val expansionIcon = if (isExpanded.value) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    Column(
        modifier = modifier
    ) {
        searchParam?.value?.let {
            OutlinedTextField(
                modifier = Modifier,
                value = it,
                onValueChange = {
                    thisValueChange(it)
                },

                trailingIcon = {
                    Icon(imageVector = expansionIcon, null,
                        Modifier.clickable { isExpanded.value = !isExpanded.value })
                },
                leadingIcon = {
                    Icon(imageVector = leadingIcon, contentDescription = null, tint = Light_Blue)
                },
                placeholder = {
                    Text(
                        text = label,
                        color = Dark_Blue,
                        fontSize = 14.sp
                    )
                }, colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Light_Blue,
                    focusedIndicatorColor = Light_Blue,
                    unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
                ),
                singleLine = true
            )
        }

        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = {
                isExpanded.value = false
            },
            modifier = Modifier.background(Color.LightGray),
        ) {
            LaunchedEffect(Unit) {
                val data = searchParam?.value?.let { appViewModel.getCodeName(it) }
                if (data != null) {
                    list?.value = data
                }
            }
            val updatedList = list?.value

            updatedList?.forEach { item ->
                DropdownMenuItem(onClick = {
                    val regex = Regex("\\(([^)]+)\\)")
                    val matchResult = regex.find(item)
                    val codeName = matchResult?.groups?.get(1)?.value
                    if (codeName != null) {
                        searchParam?.value = codeName
                        searchParam?.value?.let {
                            updatedValue(it) }
                    }

                    val regex2 = Regex("([\\w\\s]+)\\s*\\([^)]+\\)")
                    val matchResult2 = regex2.find(item)
                    val result = matchResult2?.groupValues?.get(1)
                    if (result != null) {
                        cityNameParam?.value=result
                        updateSearchWithCityName(result)
                    }

                    isExpanded.value = false
                }

                )

                {
                    Text(text = item)
                }
            }

        }
    }
}