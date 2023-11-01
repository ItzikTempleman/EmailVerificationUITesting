package com.validation_testing.emailverificationuitesting.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.validation_testing.emailverificationuitesting.R

@Composable
fun LoginValidationScreen(
    modifier: Modifier,
) {
    Text(
        modifier = Modifier.padding(20.dp),
        text = stringResource(id = R.string.app_name),
        color = colorResource(id = R.color.custom),
        fontSize = 28.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var isPasswordVisible by remember { mutableStateOf(false) }



        TextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(30.dp)),
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.email))
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            }
        )

        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Icon(
                        contentDescription = null,
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    )
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(30.dp)),
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.password))
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = {
                validateData()
            }
        ) {
            Text(
                text = stringResource(id = R.string.validate),
                fontSize = 24.sp
            )
        }
    }
}


fun validateData() {

}
