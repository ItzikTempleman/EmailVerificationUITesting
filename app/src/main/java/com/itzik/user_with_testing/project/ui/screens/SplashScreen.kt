package com.itzik.user_with_testing.project.ui.screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.ui.navigation.Graph.AUTHENTICATION
import com.itzik.user_with_testing.project.ui.navigation.Graph.HOME
import com.itzik.user_with_testing.project.utils.Constants
import com.itzik.user_with_testing.project.viewmodels.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable

fun SplashScreen(
    coroutineScope: CoroutineScope,
    appViewModel: AppViewModel,
    navController: NavHostController
) {
    Image(
        painter = painterResource(id = R.drawable.wallpaper2),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillHeight
    )

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

        navController.popBackStack()
        coroutineScope.launch {
            appViewModel.getUsers().collect {
                userList = it
            }
            if (userList.isNotEmpty() && userList.first().isSignedIn) {

                navController.navigate(HOME)
            } else {
                navController.navigate(AUTHENTICATION)
            }
        }
    }


    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (title, progressBar) = createRefs()

        Text(
            text = stringResource(id = R.string.welcome),
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .alpha(alpha = alphaAnim.value),
            color = Constants.Dark_Blue,
            fontSize = 34.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        CircularProgressIndicator(
            modifier = Modifier
                .constrainAs(progressBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(title.bottom)
                }
                .padding(top = 30.dp)
        )
    }
}
