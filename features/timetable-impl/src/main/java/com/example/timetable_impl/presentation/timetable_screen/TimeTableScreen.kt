package com.example.timetable_impl.presentation.timetable_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.theme.FefuFitTheme
import com.example.timetable_impl.R
import com.example.timetable_impl.presentation.timetable_screen.elements.EventCard
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TimeTableScreen(
    modifier: Modifier,
    viewModel: TimeTableScreenViewModel = hiltViewModel()
){
    val firstVisibleDay = remember { mutableStateOf(LocalDate.now()) }
    val lastVisibleDay = remember { mutableStateOf(LocalDate.now()) }
    val selectedDay = remember { mutableStateOf(LocalDate.now()) }

    Scaffold(
        containerColor = FefuFitTheme.color.mainAppColors.appBackgroundColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            TopBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                firstDay = firstVisibleDay.value,
                lastDay = lastVisibleDay.value
            )
            SingleRowCalendar(
                getWeekDaysInfoListener = {firstDayOfWeek:LocalDate, lastDayOfWeek:LocalDate ->
                    firstVisibleDay.value = firstDayOfWeek
                    lastVisibleDay.value= lastDayOfWeek
                },
                getSelectedDay = {day:LocalDate ->
                    selectedDay.value = day
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 72.dp)
            ){
                if(!viewModel.eventsState.value.isLoading){
                    items(viewModel.eventsState.value.data!!.size){
                        if (selectedDay.value == viewModel.eventsState.value.data!![it].day){
                            EventCard(viewModel.eventsState.value.data!![it])
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun SingleRowCalendar(
    getWeekDaysInfoListener:(firstDay:LocalDate, lastDay:LocalDate) -> Unit,
    getSelectedDay:(day:LocalDate)-> Unit
) {
    val currentDate = remember {LocalDate.now()}
    val currentMonth = remember {YearMonth.now()}
    val startMonth = remember {currentMonth.minusMonths(100).atStartOfMonth()}
    val endMonth = remember {currentMonth.plusMonths(100).atEndOfMonth()}
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val scope = rememberCoroutineScope()

    val selectedDay = remember {mutableStateOf(currentDate)}

    getSelectedDay(selectedDay.value)

    val calendarState = rememberWeekCalendarState(
        startDate = startMonth,
        endDate = endMonth,
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek
    )

    getWeekDaysInfoListener(
        calendarState.firstVisibleWeek.days[0].date,
        calendarState.firstVisibleWeek.days[6].date
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                scope.launch {
                    calendarState.animateScrollToWeek(
                        calendarState.firstVisibleWeek.days[0].date.minusWeeks(1)
                    )
                }
            })
        {
            Icon(
                modifier = Modifier.rotate(180f),
                painter = painterResource(id = R.drawable.front_arrow),
                contentDescription = "backArrow",
                tint = FefuFitTheme.color.elementsColor.elementColor
            )
        }
        WeekCalendar(
            modifier = Modifier.weight(7f),
            state = calendarState,
            dayContent = {Day(it, selectedDay)}
        )
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                scope.launch {
                    calendarState.animateScrollToWeek(
                        calendarState.firstVisibleWeek.days[0].date.plusWeeks(1)
                    )
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.front_arrow),
                contentDescription = "backArrow",
                tint = FefuFitTheme.color.elementsColor.elementColor
            )
        }
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
fun TopBar(modifier: Modifier, firstDay: LocalDate, lastDay: LocalDate) {
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
                text = "${firstDay.dayOfMonth} ${
                    if (firstDay.month.value != lastDay.month.value) numberToMonthName(
                        firstDay.month.value
                    ) + " " else ""
                }- ${lastDay.dayOfMonth} ${numberToMonthName(lastDay.month.value)}",
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

private fun numberToMonthName(monthNumber: Int):String{
    if (monthNumber < 1 || monthNumber > 12)
        throw IllegalArgumentException("Value must be in range 1..12")

    val monthDict = mapOf<Int, String>(
        1 to "января",
        2 to "февраля",
        3 to "марта",
        4 to "апреля",
        5 to "мая",
        6 to "июня",
        7 to "июля",
        8 to "августа",
        9 to "сентября",
        10 to "октября",
        11 to "ноября",
        12 to "декабря",
    )

    return monthDict[monthNumber]!!
}
