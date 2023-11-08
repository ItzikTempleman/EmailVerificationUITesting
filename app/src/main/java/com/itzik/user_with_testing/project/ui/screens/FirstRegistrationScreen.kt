package com.itzik.user_with_testing.project.ui.screens

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
import com.itzik.user_with_testing.project.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun FirstRegistrationScreen(
    coroutineScope: CoroutineScope,
    modifier: Modifier,
    navHostController: NavHostController,
    userViewModel: UserViewModel,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title) = createRefs()
        Text(
            text = stringResource(id = R.string.sign_up_first),
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }.padding(20.dp),
            color = DefaultBlue,
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
    }
}