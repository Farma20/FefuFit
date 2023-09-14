package com.example.timetable_impl.presentation.timetable_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.theme.FefuFitTheme
import com.example.timetable_impl.R
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import kotlin.time.Duration.Companion.days

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
            SingleRowCalendar()
            Spacer(modifier = modifier)
        }
    }
}

@Composable
private fun SingleRowCalendar() {
    val currentDate = remember {LocalDate.now()}
    val currentMonth = remember {YearMonth.now()}
    val startMonth = remember {currentMonth.minusMonths(100).atStartOfMonth()}
    val endMonth = remember {currentMonth.plusMonths(100).atEndOfMonth()}
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val selectedDay = remember {mutableStateOf(currentDate)}

    val calendarState = rememberWeekCalendarState(
        startDate = startMonth,
        endDate = endMonth,
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek
    )

    WeekCalendar(
        state = calendarState,
        dayContent = {Day(it, selectedDay)}
    ) {

    }
}

@Composable
private fun Day(dayData: WeekDay, selectedDay: MutableState<LocalDate>) {
    TextButton(
        modifier = Modifier.aspectRatio(1f),
        colors = ButtonDefaults.textButtonColors(
            containerColor = if (dayData.date == selectedDay.value) FefuFitTheme.color.elementsColor.elementColor else Color.Transparent,
            contentColor = if (dayData.date == selectedDay.value) FefuFitTheme.color.elementsColor.onElementsColor else FefuFitTheme.color.textColor.mainTextColor
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            selectedDay.value = dayData.date
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = numberToDayName(dayData.date.dayOfWeek.value),
                fontSize = 14.sp,
                fontWeight = FontWeight(300),
            )
            Text(
                text = dayData.date.dayOfMonth.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
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

private fun numberToDayName(dayNumber:Int):String{
    if (dayNumber < 1 || dayNumber > 7)
        throw IllegalArgumentException("Value must be in range 1..7")

    val dayDict = mapOf<Int, String>(
        1 to "пн", 2 to "вт", 3 to "ср", 4 to "чт", 5 to "пт", 6 to "сб", 7 to "вс"
    )

    return dayDict[dayNumber]!!
}
