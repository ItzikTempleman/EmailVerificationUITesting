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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.LoginGraph
import com.itzik.user_with_testing.project.ui.shapes.Blue
import com.itzik.user_with_testing.project.ui.shapes.RoundedBackGround
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay




@Composable
fun SplashScreen(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel?,
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
    }
    RoundedBackGround(Blue)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 60.dp),
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
                val (title, title2, progressBar) = createRefs()

                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = modifier
                        .constrainAs(title) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }.padding(top = 40.dp, start = 20.dp)
                        .alpha(alpha = alphaAnim.value),
                    color = Blue,
                    fontSize = 36.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(id = R.string.welcome),
                    modifier = modifier
                        .constrainAs(title2) {
                            start.linkTo(parent.start)
                            top.linkTo(title.bottom)
                        }
                        .padding(20.dp)
                        .alpha(alpha = alphaAnim.value),
                    color = Blue,
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

                CircularProgressIndicator(
                    modifier = modifier
                        .constrainAs(progressBar) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(title2.bottom)
                        }
                        .padding(20.dp)
                )
            }
        }
    }
}
