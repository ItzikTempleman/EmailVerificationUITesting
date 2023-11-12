package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.shapes.Blue
import com.itzik.user_with_testing.project.ui.shapes.RoundedBackGround
import com.itzik.user_with_testing.project.ui.shapes.Turquoise
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CreateAccountScreen(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {

    RoundedBackGround(Turquoise)

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (backBtn,title) = createRefs()
        OutlinedButton(
            border = BorderStroke(1.2.dp, White),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            onClick = {
                navHostController.navigate(LoginGraph.LoginPage.route)
            },
            modifier = modifier.size(80.dp).constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
            }.padding(20.dp),
        ) {
           Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = White
            )
        }

        Text(
            text = stringResource(id = R.string.create_new),
            modifier = modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(backBtn.bottom)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 20.dp),
            color = White,
            fontSize = 32.sp,
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, top = 130.dp,end=20.dp, bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(20.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {

                val (userName, email, password, genderBox, phoneNumber) = createRefs()

                val fullNameText = stringResource(id = R.string.full_name)
                val fullNameLabelMessage by remember { mutableStateOf(fullNameText) }
                var fullName by remember { mutableStateOf("") }



                OutlinedTextField(
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(userName) {
                            top.linkTo(parent.top)
                        }
                        .padding(20.dp),
                    value = fullName,
                    onValueChange = {
                        fullName = it
                    },
                    label = {
                       Text(text = fullNameLabelMessage)
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Color.DarkGray,
                        backgroundColor = White
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Blue
                        )
                    }
                )
            }
        }
    }
}