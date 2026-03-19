package org.coderic.protective.mobile.presentation.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coderic.protective.mobile.R
import org.coderic.protective.mobile.ui.theme.PetCareContentText
import org.coderic.protective.mobile.ui.theme.PetCareTextField
import org.coderic.protective.mobile.ui.theme.PetCareTitleText
import org.coderic.protective.mobile.ui.theme.buttonHome

@Composable
fun LoginScreen( loginViewModel: LoginViewModel ) {
    Box {
        // Footer.
        PetCareContentText( stringResource(id = R.string.footerText), 12, Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(buttonHome)
                .padding(top = 40.dp, bottom = 16.dp)
        )
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.login_background),
            alpha = 0.2f,
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
        )
        Body( modifier = Modifier.align(Alignment.Center), loginViewModel )
    }
}

@Composable
fun Body( modifier: Modifier, loginViewModel: LoginViewModel ) {
    val uiState by loginViewModel.uiState.observeAsState(LoginUiState.Idle)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isLoading = uiState is LoginUiState.Loading
    val errorMessage = (uiState as? LoginUiState.Error)?.message

    Column(
        modifier = modifier.padding( 32.dp )
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp),
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = "Coco Icon"
        )

        PetCareTextField(value = email, placeholder = R.string.placeholderEmail, Icons.Filled.Email, onValueChange = {
            email = it
            if (uiState is LoginUiState.Error) loginViewModel.clearError()
        })
        Spacer(modifier = Modifier.height(16.dp))
        PetCareTextField(value = password, placeholder = R.string.placeholderPass, imageVector = Icons.Filled.Lock, onValueChange = {
            password = it
            if (uiState is LoginUiState.Error) loginViewModel.clearError()
        })
        Spacer(modifier = Modifier.height(8.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
            )
        }

        TextButton(
            modifier = Modifier.align( Alignment.End ),
            onClick = { /* TODO: Implementar sistema de recuperación de contraseña. */ }
        ) {
            PetCareContentText(text = stringResource(id = R.string.forget), size = 16, color = buttonHome )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally),
            onClick = { loginViewModel.onLoginButtonClick(email, password) },
            enabled = !isLoading,
            shape = RoundedCornerShape(10.dp ),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonHome
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(22.dp))
            } else {
                PetCareTitleText(text = stringResource(id = R.string.LOGIN), size = 22 )
            }
        }

        // Divider
        Spacer(modifier = Modifier.height(16.dp))
        MyDivider()
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            ButtonLogWith(
                text = stringResource(id = R.string.loginGoogle),
                drawable = R.drawable.ic_google
            )
            Spacer(modifier = Modifier.height(6.dp))
            ButtonLogWith(
                text = stringResource(id = R.string.loginFacebook),
                drawable = R.drawable.ic_facebook
            )
            Spacer(modifier = Modifier.height(6.dp))
            ButtonLogWith(
                text = stringResource(id = R.string.loginApple),
                drawable = R.drawable.ic_apple
            )
        }
    }
}
@Composable
fun MyDivider() {
    Row( verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            Modifier
                .padding(end = 8.dp)
                .weight(1f))
        PetCareContentText(text = stringResource(id = R.string.orConnect), size = 16, Color.Gray )
        HorizontalDivider(
            Modifier
                .padding(start = 8.dp)
                .weight(1f))
    }
}

@Composable
fun ButtonLogWith(text: String, @DrawableRes drawable: Int) {
    Button(
        onClick = { },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonHome
        )
    )
    {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = drawable),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        PetCareContentText(
            text = text, size = 18, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp) )
    }
}