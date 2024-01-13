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
import com.itzik.user_with_testing.project.viewmodels.AppViewModel

@Composable
fun DropDownMenuScreen(
    modifier: Modifier,
    searchParam: MutableState<String>,
    appViewModel: AppViewModel,
    isExpanded: MutableState<Boolean>,
    list: MutableState<List<String>>,
    thisValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    updatedValue: (String) -> Unit,
) {

    val expansionIcon = if (isExpanded.value) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    Column(
        modifier=modifier
    ) {
    OutlinedTextField(
        modifier = Modifier,
        value = searchParam.value,
        onValueChange = {
            thisValueChange(it)
        },
        trailingIcon = {
            Icon(imageVector = expansionIcon, null,
                Modifier.clickable { isExpanded.value = !isExpanded.value })
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null, tint = Dark_Blue)
        },
        placeholder = {
            Text(
                text = label,
                color = Dark_Blue,
                fontSize = 14.sp
            )
        }, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Dark_Blue,
            focusedIndicatorColor = Dark_Blue,
            unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
        ),
        singleLine = true
    )

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
                        updatedValue(searchParam.value)
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

