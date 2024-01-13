package com.itzik.user_with_testing.project.ui.semantics

//            Column(
//                modifier = Modifier
//                    .width(205.dp)
//                    .padding(4.dp)
//            ) {
//                OutlinedTextField(
//                    value = searchDeparture,
//                    onValueChange = {
//                        searchDeparture = it
//                                    },
//                    modifier = Modifier,
//                    placeholder = {
//                        Text(
//                            text = stringResource(id = R.string.search_departure_city),
//                            color = Dark_Blue,
//                            fontSize = 14.sp
//                        )
//                    },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.FlightTakeoff,
//                            contentDescription = null,
//                            tint = Dark_Blue
//                        )
//                    },
//                    trailingIcon = {
//                        Icon(departureIcon, null,
//                            Modifier.clickable { isDepartureExpanded = !isDepartureExpanded })
//                    },
//                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = Color.Transparent,
//                        cursorColor = Dark_Blue,
//                        focusedIndicatorColor = Dark_Blue,
//                        unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
//                    ),
//                    singleLine = true
//
//                )
//                DropdownMenu(
//                    expanded = isDepartureExpanded,
//                    onDismissRequest = { isDepartureExpanded = false },
//                    modifier = Modifier
//                ) {
//                    LaunchedEffect(Unit) {
//                        val data = appViewModel.getCodeName(searchDeparture)
//                        list.value = data
//                    }
//                    val updatedList = list.value
//                    updatedList.forEach { item ->
//                        DropdownMenuItem(onClick = {
//                            val regex = Regex("\\(([^)]+)\\)")
//                            val matchResult = regex.find(item)
//                            val codeName = matchResult?.groups?.get(1)?.value
//
//                            if (codeName != null) {
//                                searchDeparture = codeName
//                            }
//                            isDepartureExpanded = false
//                        }) {
//                            Text(text = item)
//                        }
//                    }
//                }
//
//            }
//            Column(
//                modifier = Modifier
//                    .width(205.dp)
//                    .padding(4.dp)
//            ) {
//                OutlinedTextField(
//                    value = searchDestination,
//                    onValueChange = { searchDestination = it },
//                    modifier = Modifier,
//                    placeholder = {
//                        Text(
//                            text = stringResource(id = R.string.search_destination_city),
//                            color = Dark_Blue,
//                            fontSize = 14.sp
//                        )
//                    },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.FlightLand,
//                            contentDescription = null,
//                            tint = Dark_Blue
//                        )
//                    },
//                    trailingIcon = {
//                        Icon(desinationIcon, null,
//                            Modifier.clickable { isDestinationExpanded = !isDestinationExpanded })
//                    },
//                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = Color.Transparent,
//                        cursorColor = Dark_Blue,
//                        focusedIndicatorColor = Dark_Blue,
//                        unfocusedIndicatorColor = Color.DarkGray.copy(0.3f)
//                    ),
//                    singleLine = true
//                )
//                DropdownMenu(
//                    expanded = isDestinationExpanded,
//                    onDismissRequest = { isDestinationExpanded = false },
//                    modifier = Modifier
//                ) {
//                    LaunchedEffect(Unit) {
//                        val data = appViewModel.getCodeName(searchDestination)
//                        list.value = data
//                    }
//                    val updatedDestinationList = list.value
//                    updatedDestinationList.forEach { destinationItem ->
//                        DropdownMenuItem(onClick = {
//                            val regex = Regex("\\(([^)]+)\\)")
//                            val matchResult = regex.find(destinationItem)
//                            val codeName = matchResult?.groups?.get(1)?.value
//                            if (codeName != null) {
//                                searchDestination = codeName
//                            }
//                            isDestinationExpanded = false
//                        }) {
//                            Text(text = destinationItem)
//                        }
//                    }
//                }
//            }
//        }