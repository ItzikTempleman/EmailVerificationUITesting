package com.itzik.user_with_testing.project.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.semantics.RoundedBackGround
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier,
    navHostController: NavHostController,
) {

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
        navHostController.navigate(LoginGraph.LoginPage.route)
        //navHostController.navigate(HomeGraph.HomePage.route)
    }
    RoundedBackGround(Dark_Green, White)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (title, title2) = createRefs()

        Text(
            text = stringResource(id = R.string.app_name),
            modifier = modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 20.dp)
                .alpha(alpha = alphaAnim.value),
            color = Color.White,
            fontSize = 36.sp,

        )

        Text(
            text = stringResource(id = R.string.welcome),
            modifier = modifier
                .constrainAs(title2) {
                    start.linkTo(parent.start)
                    top.linkTo(title.bottom)
                    end.linkTo(parent.end)
                }
                .padding(8.dp)
                .alpha(alpha = alphaAnim.value),
            color = Color.White,
            fontSize = 32.sp,

        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 130.dp),
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
                val (progressBar) = createRefs()


                CircularProgressIndicator(
                    modifier = modifier
                        .constrainAs(progressBar) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }
        }
    }
}
