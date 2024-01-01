package com.itzik.user_with_testing.project.ui.screens

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.navigation.BottomNavGraph


import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.semantics.RoundedBackGround
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(
    coroutineScope: CoroutineScope,
    userViewModel: UserViewModel,
    modifier: Modifier,
    navHostController: NavHostController,
) {
    var userList by remember {
        mutableStateOf(listOf<User>())
    }
    var startAnim by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        ), label = ""
    )
    LaunchedEffect(key1 = true) {
        startAnim = true
        delay(2000)

        navHostController.popBackStack()
        coroutineScope.launch {
            userViewModel.getUsers().collect {
                userList = it
                Log.d("TAG", "$it")
            }
            if (userList.isNotEmpty() && userList.first().isSignedIn) {
                navHostController.navigate(BottomNavGraph.HomePage.route)
            } else
                navHostController.navigate(LoginGraph.LoginPage.route)
            }
    }


    RoundedBackGround(White, White)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (title, progressBar) = createRefs()

        Text(
            text = stringResource(id = R.string.welcome),
            modifier = modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .alpha(alpha = alphaAnim.value),
            color = Black,
            fontSize = 32.sp
        )

        CircularProgressIndicator(
            modifier = modifier
                .constrainAs(progressBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(title.bottom)
                }
                .padding(top = 30.dp)
        )
    }
}
