package com.example.fefufit.presentation.main_menu

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Spacer(modifier = Modifier.height(26.dp))
            QrCard()
            Spacer(modifier = Modifier.height(18.dp))
            NearEventSpace()
        }
    }
}

@Composable
fun NearEventSpace() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ближайшее занятие",
                fontSize = 22.sp,
                fontWeight = FontWeight(400),
                color = FefuFitTheme.color.textColor.mainTextColor,
            )
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {  },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Все записи ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = FefuFitTheme.color.textColor.secondaryTextColor,
                )
                Icon(
                    painter = painterResource(id = R.drawable.next_arrows),
                    contentDescription = "nextArrows",
                    tint = FefuFitTheme.color.textColor.secondaryTextColor,
                )
            }
        }
    }
}

@Composable
private fun QrCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = FefuFitTheme.color.mainAppColors.appBlueColor,
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
            ) {
                Text(
                    text = "Ваш пропуск на зянятие",
                    color = FefuFitTheme.color.textColor.whiteTextColor,
                    fontSize = 17.sp,
                    fontWeight = FontWeight(500),
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = "Покажите QR-код\n" +
                            "администратору",
                    color = FefuFitTheme.color.textColor.whiteTextColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(300),
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Image(
                painter = painterResource(id = R.drawable.fake_qr),
                contentDescription = "qr"
            )
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


