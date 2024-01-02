package com.itzik.user_with_testing.project.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileScreen(
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(Color.Yellow)
    ) {
        Text(text ="Profile")
    }

//
//    var userList by remember {
//        mutableStateOf(listOf<User>())
//    }
//    var user = mockEmptyUser()
//    coroutineScope.launch {
//        userViewModel.getUsers().collect {
//            userList = it
//        }
//    }
//
//    RoundedBackGround(White, White)
//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val (title, logOut, fullFirstName, familyName, age, birthDate, email, gender, phoneNumber) = createRefs()
//
//        Text(
//            text = stringResource(id = R.string.home),
//            modifier = Modifier
//                .constrainAs(title) {
//                    start.linkTo(parent.start)
//                    top.linkTo(parent.top)
//                }
//                .padding(16.dp),
//
//            fontSize = 20.sp,
//
//        )
//        TextButton(
//
//            onClick = {
//                navHostController.navigate(LoginGraph.LoginPage.route)
//                coroutineScope.launch {
//                    user.isSignedIn = false
//                    userViewModel.updateIsSignIn(user)
//                }
//            },
//            modifier = modifier
//                .constrainAs(logOut) {
//                    end.linkTo(parent.end)
//                    top.linkTo(parent.top)
//                }
//                .padding(16.dp)
//        ) {
//            Text(
//                text = stringResource(id = R.string.sign_out),
//                fontSize = 20.sp,
//            )
//        }
//
//        if (userList.isNotEmpty()) {
//            user = userList.first()
//        }
////        else {
////            coroutineScope.launch {
////
////            }
////        }
//        Text(
//            modifier = modifier
//                .constrainAs(fullFirstName) {
//                    start.linkTo(parent.start)
//                    top.linkTo(title.bottom)
//                }
//                .padding(8.dp),
//            text = user.firstName.first() + " " + user.firstName[1]
//        )
//        Text(
//            modifier = modifier
//                .constrainAs(familyName) {
//                    start.linkTo(fullFirstName.end)
//                    top.linkTo(fullFirstName.top)
//                    bottom.linkTo(fullFirstName.bottom)
//                }
//                .padding(8.dp),
//            text = user.familyName
//        )
//        Text(
//            modifier = modifier
//                .constrainAs(birthDate) {
//                    start.linkTo(parent.start)
//                    top.linkTo(fullFirstName.bottom)
//                }
//                .padding(8.dp),
//            text = user.birthDate
//        )
//        Text(
//            modifier = modifier
//                .constrainAs(age) {
//                    start.linkTo(parent.start)
//                    top.linkTo(birthDate.bottom)
//                }
//                .padding(8.dp),
//            text = user.age.toString()
//        )
//
//        Text(
//            modifier = modifier
//                .constrainAs(email) {
//                    start.linkTo(parent.start)
//                    top.linkTo(age.bottom)
//                }
//                .padding(8.dp),
//            text = user.email
//        )
//
//
//        Text(
//            modifier = modifier
//                .constrainAs(gender) {
//                    start.linkTo(parent.start)
//                    top.linkTo(email.bottom)
//                }
//                .padding(8.dp),
//            text = user.gender
//        )
//
//        Text(
//            modifier = modifier
//                .constrainAs(phoneNumber) {
//                    start.linkTo(parent.start)
//                    top.linkTo(gender.bottom)
//                }
//                .padding(8.dp),
//            text = user.phoneNumber
//        )
//    }
}



