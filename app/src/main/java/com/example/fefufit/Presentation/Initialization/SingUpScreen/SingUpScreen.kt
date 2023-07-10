package com.example.fefufit.Presentation.Initialization.SingUpScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefufit.Presentation.theme.BlackApp
import com.example.fefufit.Presentation.theme.BlueApp
import com.example.fefufit.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpScreen() {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            UppBar(modifier = Modifier)
        }
    ) {scaffoldPadding->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(scaffoldPadding.calculateTopPadding()))
            RegistrationPager(Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Личные данные",
                fontSize = 22.sp,
                fontWeight = FontWeight(600),
                color = BlackApp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UppBar(modifier: Modifier){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Регистрация",
                fontSize = 22.sp,
                fontWeight = FontWeight(400),
                color = BlackApp
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.back_arrow),
                    tint = BlueApp,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun RegistrationPager(modifier: Modifier){
    Row(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, BlueApp, CircleShape)
                .size(32.dp)
                .background(BlueApp, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "1",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .border(1.dp, BlueApp, CircleShape)
                .size(32.dp)
                .background(Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "2",
                color = BlueApp,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
    }
}