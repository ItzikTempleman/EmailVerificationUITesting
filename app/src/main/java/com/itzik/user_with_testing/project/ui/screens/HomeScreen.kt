package com.itzik.user_with_testing.project.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.R
import com.itzik.user_with_testing.project.navigation.Dark_Green
import com.itzik.user_with_testing.project.viewmodels.UserViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    modifier: Modifier,
    userViewModel: UserViewModel,
) {

    val user = userViewModel.user

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            Log.d("TAG2", "User passed successfully: $user")
            val (title) = createRefs()
            Text(
                text = stringResource(id = R.string.home_screen),
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .padding(20.dp),
                color = Dark_Green,
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }
    }
