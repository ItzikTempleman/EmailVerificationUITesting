package com.itzik.user_with_testing.project.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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
import com.itzik.user_with_testing.project.navigation.BottomBarGraph
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.semantics.GenericButton
import com.itzik.user_with_testing.project.ui.semantics.GenericRoundedButton
import com.itzik.user_with_testing.project.ui.semantics.RoundedBackGround
import com.itzik.user_with_testing.project.utils.Constants.Dark_Blue
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CreateAccountScreen(
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    appViewModel: AppViewModel
) {
    RoundedBackGround(
        topColor = White,
        bottomColor = White
    )

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (backBtn, title, basicInfoCard, dateAndPhoneLayout, createUserBtn) = createRefs()

        GenericRoundedButton(
            modifier = Modifier
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(20.dp),
            imageVector = Icons.Default.ArrowBack,
            onClickFunction = {
                navController.navigate(LoginGraph.SignUp.route)
            },
            outerTint = White,
            iconTint = White,
            innerIconColor = Dark_Blue,
            1.2.dp
        )
        Text(
            text = stringResource(id = R.string.create_new),
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(backBtn.end)
                    top.linkTo(backBtn.top)
                }
                .padding(top = 20.dp),
            color = Black,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic
        )

        CreateUserTopHalf(
            coroutineScope = coroutineScope,
            modifier = Modifier
                .constrainAs(basicInfoCard) {
                    top.linkTo(title.bottom)
                }
                .height(420.dp)
                .fillMaxWidth()
                .padding(top = 20.dp, start = 12.dp, end = 12.dp),
            navController = navController,
            appViewModel = appViewModel
        )

        CreateUserBottomHalf(
            modifier = Modifier
                .constrainAs(dateAndPhoneLayout) {
                    top.linkTo(basicInfoCard.bottom)
                }
                .padding(horizontal = 12.dp),
            appViewModel = appViewModel,

            )

        GenericButton(
            modifier = Modifier
                .constrainAs(createUserBtn) {
                    top.linkTo(dateAndPhoneLayout.bottom)
                }
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 60.dp),
            onClick = {
                coroutineScope.launch {
                    if (appViewModel.isAllFieldsOk()) {
                        appViewModel.createUser()
                        val userModel = appViewModel.user
                        if (userModel != null) {
                            userModel.isSignedIn=true
                            appViewModel.updateIsSignIn(userModel)
                            appViewModel.saveUser(userModel)

                        }
                        navController.popBackStack()
                        navController.navigate(BottomBarGraph.SearchFlights.route)

                    } else appViewModel.setErrors()
                }
            },
            buttonColor = Dark_Blue,
            text = stringResource(id = R.string.create_account),
            textColor = White,
            roundedRadius = 36.dp
        )
    }
}


