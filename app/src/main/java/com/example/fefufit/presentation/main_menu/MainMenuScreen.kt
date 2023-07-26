package com.example.fefufit.presentation.main_menu

import android.annotation.SuppressLint
import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fefufit.R
import com.example.fefufit.presentation.theme.FefuFitTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen() {
    Scaffold(
        containerColor = FefuFitTheme.color.mainAppColors.appBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(26.dp))
            MainMenuUppBar()
        }
    }
}

@Composable
private fun MainMenuUppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
       Text(
           modifier = Modifier.fillMaxWidth(0.7f),
           text = buildAnnotatedString {
               append("Время тренировки, ")
               withStyle(style = SpanStyle(fontWeight = FontWeight(300))) {
                   append("Александр!")
               }
           },
           fontSize = 22.sp,
           fontWeight = FontWeight(500),
           color  = FefuFitTheme.color.textColor.mainTextColor,
       )
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {}
            ){
                Icon(
                    painter = painterResource(id = R.drawable.notification_bell),
                    contentDescription = "bell",
                    tint = FefuFitTheme.color.textColor.mainTextColor
                )
            }
            IconButton(
                onClick = {},
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "person",
                    tint = FefuFitTheme.color.elementsColor.elementColor
                )
            }
        }
    }
}
