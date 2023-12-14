package com.itzik.user_with_testing.project.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.Light_Blue
import com.itzik.user_with_testing.project.ui.semantics.RoundedBackGround
import com.itzik.user_with_testing.project.viewmodels.UserViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    modifier: Modifier,
    userViewModel: UserViewModel,
) {

    val user = userViewModel.user
    RoundedBackGround(Light_Blue, Color.White)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, infoCard) = createRefs()
        Text(
            text = stringResource(id = R.string.home_screen),
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(16.dp),
            color = White,
            fontSize = 32.sp,
        )

        Card(
            modifier = Modifier
                .constrainAs(infoCard) {
                    top.linkTo(title.bottom)
                }
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 200.dp),
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

                val (fullFirstName, familyName, age, birthDate, email, gender, phoneNumber) = createRefs()
                if (user != null) {
                    Text(
                        modifier = modifier
                            .constrainAs(fullFirstName) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            }
                            .padding(8.dp),
                        text = user.firstName[0] + " " + user.firstName[1]
                    )
                    Text(
                        modifier = modifier
                            .constrainAs(familyName) {
                                start.linkTo(fullFirstName.end)
                                top.linkTo(parent.top)
                            }
                            .padding(8.dp),
                        text = user.familyName
                    )
                    Text(
                        modifier = modifier
                            .constrainAs(birthDate) {
                                start.linkTo(parent.start)
                                top.linkTo(fullFirstName.bottom)
                            }
                            .padding(8.dp),
                        text = user.birthDate
                    )
                    Text(
                        modifier = modifier
                            .constrainAs(age) {
                                start.linkTo(parent.start)
                                top.linkTo(birthDate.bottom)
                            }
                            .padding(8.dp),
                        text = user.age.toString()
                    )

                    Text(
                        modifier = modifier
                            .constrainAs(email) {
                                start.linkTo(parent.start)
                                top.linkTo(age.bottom)
                            }
                            .padding(8.dp),
                        text = user.email
                    )


                    Text(
                        modifier = modifier
                            .constrainAs(gender) {
                                start.linkTo(parent.start)
                                top.linkTo(email.bottom)
                            }
                            .padding(8.dp),
                        text = user.gender
                    )

                    Text(
                        modifier = modifier
                            .constrainAs(phoneNumber) {
                                start.linkTo(parent.start)
                                top.linkTo(gender.bottom)
                            }
                            .padding(8.dp),
                        text = user.phoneNumber
                    )
                }
            }
        }
    }
}
