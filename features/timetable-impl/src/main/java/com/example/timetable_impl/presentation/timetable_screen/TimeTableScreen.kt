package com.example.timetable_impl.presentation.timetable_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.theme.FefuFitTheme
import com.example.timetable_impl.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TimeTableScreen(
    modifier: Modifier,
){
    Scaffold(
        containerColor = FefuFitTheme.color.mainAppColors.appBackgroundColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            TopBar(modifier.fillMaxWidth())
            Spacer(modifier = modifier)
        }
    }
}

@Composable
fun TopBar(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(
                text = "Расписание занятий",
                fontSize = 22.sp,
                fontWeight = FontWeight(500),
                color = FefuFitTheme.color.textColor.mainTextColor,
            )
            Text(
                text = "20-26 июля",
                fontSize = 22.sp,
                fontWeight = FontWeight(300),
                color = FefuFitTheme.color.textColor.mainTextColor,
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.filters),
                contentDescription = null,
                tint = FefuFitTheme.color.textColor.mainTextColor
            )
        }
    }
}
