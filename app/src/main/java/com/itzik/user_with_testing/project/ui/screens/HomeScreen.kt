package com.itzik.user_with_testing.project.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.ui.semantics.RoundedBackGround
import com.itzik.user_with_testing.project.utils.mockEmptyUser
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    modifier: Modifier,
    userViewModel: UserViewModel,
) {


    var userList by remember {
        mutableStateOf(listOf<User>())
    }
    var user = mockEmptyUser()
    coroutineScope.launch {
        userViewModel.getUsers().collect {
            userList = it
        }
    }
    RoundedBackGround(White, White)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, fullFirstName, familyName, age, birthDate, email, gender, phoneNumber) = createRefs()

        Text(
            text = stringResource(id = R.string.home),
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(16.dp),
            color = Black,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic
        )

        if (userList.isNotEmpty()) {
            user = userList[0]
        }
        Text(
            modifier = modifier
                .constrainAs(fullFirstName) {
                    start.linkTo(parent.start)
                    top.linkTo(title.bottom)
                }
                .padding(8.dp),
            text = user.firstName[0] + " " + user.firstName[1]
        )
        Text(
            modifier = modifier
                .constrainAs(familyName) {
                    start.linkTo(fullFirstName.end)
                    top.linkTo(fullFirstName.top)
                    bottom.linkTo(fullFirstName.bottom)
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



