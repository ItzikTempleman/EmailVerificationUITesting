package com.itzik.user_with_testing.project.ui.semantics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun DropdownTextField(
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var showDropdown by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = text,
            onValueChange = {
                text = it
                showDropdown = text.text.length >= 5
            },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = showDropdown,
            onDismissRequest = { showDropdown = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onItemSelected(item)
                    showDropdown = false
                    text = TextFieldValue(item)
                }) {
                    Text(item)
                }
            }
        }
    }
}