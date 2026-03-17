package org.coderic.protective.mobile.presentation.getStarted

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.coderic.protective.mobile.R
import org.coderic.protective.mobile.ui.theme.PetCareTitleText
import org.coderic.protective.mobile.ui.theme.buttonHome
import org.coderic.protective.mobile.ui.theme.seed


@Composable
fun GetStartedScreen( getStartedViewModel: GetStartedViewModel ) {
    val image by getStartedViewModel.image.observeAsState( initial = R.drawable.get_started_1 )
    Box( modifier = Modifier.fillMaxSize() ) {
        ImageBackground( image = image )
        BottomSheet(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(360.dp),
            getStartedViewModel
        )
    }
}
@Composable
fun ImageBackground(image: Int) {
    Crossfade( targetState = image, label = "Image", animationSpec = tween(500, easing = EaseIn) ) {
        Image(
            painter = painterResource(id = it),
            contentDescription = "Pet Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Composable
fun BottomSheet(modifier: Modifier, getStartedViewModel: GetStartedViewModel) {
    val screen by getStartedViewModel.screen.observeAsState(initial = 1)
    Crossfade(
        targetState = screen,
        label = "Screens",
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),

        animationSpec = tween(500, easing = EaseIn)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Chipset(if (screen == 1) seed else Color.Transparent)
                Spacer(modifier = Modifier.width(8.dp))
                Chipset(if (screen == 2) seed else Color.Transparent)
                Spacer(modifier = Modifier.width(8.dp))
                Chipset(if (screen == 3) seed else Color.Transparent)
            }
            when (it) {
                1 -> ContentScreenOne(getStartedViewModel)
                2 -> ContentScreenTwo(getStartedViewModel)
                3 -> ContentScreenThree(getStartedViewModel)
            }
        }
    }
}
@Composable
fun ButtonNext(text: Int, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = seed,
            contentColor = Color.White
        )
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Arrow"
        )
    }
}

@Composable
fun ContentScreenOne(getStartedViewModel: GetStartedViewModel) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher),
        contentDescription = "icon",
        modifier = Modifier.size(140.dp)
    )
    PetCareTitleText( stringResource(id = R.string.welcome ), 24 )
    Spacer(modifier = Modifier.height(16.dp))
    PetCareTitleText( text = stringResource( id = R.string.desc_11 ), size = 16, Color.Gray, modifier = Modifier.padding( start = 12.dp, end = 12.dp ) )
    Spacer(modifier = Modifier.height(16.dp))
    ButtonNext(text = R.string.next) {
        getStartedViewModel.onClick(2)
    }
}

@Composable
fun ContentScreenTwo(getStartedViewModel: GetStartedViewModel) {

    Spacer(modifier = Modifier.height(32.dp))
    PetCareTitleText( stringResource(id = R.string.now ), 24 )
    // Descriptions
    Spacer(modifier = Modifier.height(16.dp))
    PetCareTitleText( text = stringResource( id = R.string.desc_21 ), size = 16, Color.Gray, modifier = Modifier.padding( start = 12.dp, end = 12.dp ) )
    Spacer(modifier = Modifier.height(16.dp))
    PetCareTitleText( text = stringResource( id = R.string.desc_22 ), size = 16, Color.Gray, modifier = Modifier.padding( start = 12.dp, end = 12.dp ) )
    Spacer(modifier = Modifier.height(16.dp))
    PetCareTitleText( text = stringResource( id = R.string.desc_23 ), size = 16, Color.Gray, modifier = Modifier.padding( start = 12.dp, end = 12.dp ) )
    Spacer(modifier = Modifier.height(32.dp))
    ButtonNext(text = R.string.next) {
        getStartedViewModel.onClick(3)
    }
}

@Composable
fun ContentScreenThree(getStartedViewModel: GetStartedViewModel) {

    Spacer(modifier = Modifier.height(32.dp))
    PetCareTitleText( stringResource(id = R.string.weprovide ), 24 )
    // Descriptions
    Spacer(modifier = Modifier.height(16.dp))
    PetCareTitleText( text = stringResource( id = R.string.desc_31 ), size = 16, Color.Gray, modifier = Modifier.padding( start = 12.dp, end = 12.dp ) )
    Spacer(modifier = Modifier.height(16.dp))
    PetCareTitleText( text = stringResource( id = R.string.desc_32 ), size = 16, Color.Gray, modifier = Modifier.padding( start = 12.dp, end = 12.dp ) )
    Spacer(modifier = Modifier.height(16.dp))

    ButtonNext(text = R.string.btnStarted) {

    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        PetCareTitleText( text = stringResource(id = R.string.haveAccount), size = 16 )
        TextButton(onClick = { getStartedViewModel.onLoginBtnClick() }) {
            PetCareTitleText(text = stringResource(id = R.string.login), size = 16, color = buttonHome )
        }
    }
}

@Composable
fun Chipset(color: Color) {
    Spacer(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .border(BorderStroke(1.dp, getBorderColor(color = color)), RoundedCornerShape(10.dp))
            .background(color)
            .width(25.dp)
            .height(10.dp)
    )
}

fun getBorderColor(color: Color): Color {
    return if (color == Color.Transparent) {
        Color.LightGray
    } else {
        color
    }
}