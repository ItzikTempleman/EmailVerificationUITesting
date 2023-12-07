package com.itzik.user_with_testing.project.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.navigation.Yellow
import com.itzik.user_with_testing.project.ui.screen_sections.CreateUserBottomHalf
import com.itzik.user_with_testing.project.ui.screen_sections.CreateUserTopHalf
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.ui.shapes.RoundedBackGround
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun CreateAccountScreen(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {

//    val userAge = userViewModel.age
//    var isUserAgeValid by remember {
//        mutableStateOf(false)
//    }
//
//    if (userAge < 18) {
//        isUserAgeValid = false
//    }
//
//    if (!isUserAgeValid) ShowDateErrorMessage("Must be at least 18 year old to sign up")

    RoundedBackGround(
        topColor = Dark_Green,
        bottomColor = White
    )

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (backBtn, title, basicInfoCard, dateAndPhoneLayout, createUserBtn) = createRefs()

        GenericRoundedButton(
            modifier = modifier
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(20.dp),
            imageVector = Icons.Default.ArrowBack,
            onClickFunction = {
                navHostController.navigate(LoginGraph.LoginPage.route)
            },
            outerTint = White,
            iconTint=White,
            innerIconColor = Dark_Green,
            1.2.dp
        )
        Text(
            text = stringResource(id = R.string.create_new),
            modifier = modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(backBtn.top)
                    end.linkTo(parent.end)
                }
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            color = White,
            fontSize = 32.sp
        )

        CreateUserTopHalf(
            coroutineScope = coroutineScope,
            modifier = Modifier
                .constrainAs(basicInfoCard) {
                    top.linkTo(title.bottom)
                }
                .height(420.dp)
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            navHostController = navHostController,
            userViewModel = userViewModel
        )

        CreateUserBottomHalf(
            coroutineScope = coroutineScope,
            modifier = Modifier
                .constrainAs(dateAndPhoneLayout) {
                    top.linkTo(basicInfoCard.bottom)
                }
                .padding(horizontal = 20.dp),
            navHostController = navHostController,
            userViewModel = userViewModel,
        )

        GenericButton(
            modifier = Modifier
                .constrainAs(createUserBtn) {
                    top.linkTo(dateAndPhoneLayout.bottom)
                }
                .fillMaxWidth()
                .padding(start = 32.dp,end=32.dp, top = 60.dp),
            onClick = {

            },
            buttonColor = Yellow,
            text = stringResource(id = R.string.create_account),
            textColor = White,
            roundedRadius = 4.dp
        )
    }
}

@Composable
fun ShowDateErrorMessage(message: String) {
    Toast.makeText(
        LocalContext.current,
        message,
        Toast.LENGTH_LONG
    ).show()
}