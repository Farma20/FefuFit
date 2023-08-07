package com.example.fefufit.presentation.main_menu

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fefufit.R
import com.example.fefufit.data.remote.models.events_data_models.UserBookingDataModelItem
import com.example.fefufit.data.remote.models.services_data_models.UserServicesDataModelItem
import com.example.fefufit.presentation.main_menu.models.ActiveServicesState
import com.example.fefufit.presentation.main_menu.models.NearBookingDataState
import com.example.fefufit.presentation.main_menu.models.UserDataState
import com.example.fefufit.presentation.theme.FefuFitTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)
@Composable
fun MainMenuScreen(
    viewModel:MainMenuViewModel = hiltViewModel()
) {
    //data states
    val userDataState = viewModel.userDataState.value
    val nearBookingState = viewModel.nearBookingState.value
    val activeUserServicesState = viewModel.activeServicesState.value

    //pages variables
    val pagerState = rememberPagerState()

    Scaffold(
        containerColor = FefuFitTheme.color.mainAppColors.appBackgroundColor,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(26.dp))
            MainMenuUppBar(userDataState)
            Spacer(modifier = Modifier.height(26.dp))
            QrCard()
            Spacer(modifier = Modifier.height(18.dp))
            NearEventSpace(nearBookingState)
            Spacer(modifier = Modifier.height(18.dp))
            ActiveServicesSpace(
                pagerState,
                activeUserServicesState,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ActiveServicesSpace(
    pagerState: PagerState,
    activeUserServicesState: ActiveServicesState
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Действующие абонементы",
                fontSize = 22.sp,
                fontWeight = FontWeight(400),
                color = FefuFitTheme.color.textColor.mainTextColor,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (activeUserServicesState.isLoading)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = FefuFitTheme.color.elementsColor.elementColor
                )
            }
        else if (activeUserServicesState.error != null)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = activeUserServicesState.error
                )
            }
        else if (activeUserServicesState.data == null){
            Text(
                text = "Ближайших занятий нет"
            )
        }
        else{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    count = activeUserServicesState.data.size,
                    state = pagerState
                ) {id->
                    ActiveServicesCard(activeUserServicesState.data[id])
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = FefuFitTheme.color.elementsColor.elementColor
                )
            }
        }

    }
}

@Composable
fun ServiceCircle(number: Int, visited: Boolean){
    val backgroundColor =
        if (visited)
            FefuFitTheme.color.elementsColor.elementColor
        else
            Color.Transparent

    Box(
        modifier = Modifier
            .border(1.dp, FefuFitTheme.color.elementsColor.elementColor, CircleShape)
            .size(32.dp)
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ){
        if(visited){
            Icon(
                painter = painterResource(id = R.drawable.check_mark),
                contentDescription = "check_mark",
                tint = FefuFitTheme.color.elementsColor.onElementsColor
            )
        }else{
            Text(
                text = "$number",
                color = FefuFitTheme.color.elementsColor.elementColor,
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 22.sp,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ActiveServicesCard(data: UserServicesDataModelItem) {
    Card(
        modifier = Modifier.padding(horizontal = 18.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = FefuFitTheme.color.mainAppColors.appCardColor
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(15.dp)
                ) {
                    Text(
                        text = data.serviceName,
                        fontSize = 17.sp,
                        fontWeight = FontWeight(500),
                        color = FefuFitTheme.color.textColor.mainTextColor,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.geo_pos_icon),
                            contentDescription = "geo_icon",
                            tint = FefuFitTheme.color.textColor.mainTextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Корпус S, зал аэробики",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(300),
                            color = FefuFitTheme.color.textColor.mainTextColor,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        for (i in 1..data.planCapacity){
                            val visit = i <= data.eventsDone
                            ServiceCircle(i, visit)
                            Spacer(modifier = Modifier
                                .width(2.dp)
                                .height(34.dp))
                        }
                    }
                }
            }
            Row() {
                DashedLine()
                TextButton(
                    enabled = false,
                    modifier = Modifier
                        .rotateVertically(VerticalRotation.COUNTER_CLOCKWISE)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    ),
                    onClick = {}
                ) {
                    Text(
                        modifier = Modifier,
                        text = "до 25.07.2023",
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(300),
                        color = FefuFitTheme.color.textColor.secondaryTextColor,
                    )
                }
            }
        }
    }
}

@Composable
fun NearEventSpace(nearBookingState: NearBookingDataState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
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
                    .clickable { },
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
        Spacer(modifier = Modifier.height(10.dp))
        if (nearBookingState.isLoading)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = FefuFitTheme.color.elementsColor.elementColor
                )
            }
        else if (nearBookingState.error != null)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = nearBookingState.error
                )
            }
        else if (nearBookingState.data == null){
            Text(
                text = "Ближайших занятий нет"
            )
        }
        else{
            NearEventCard(nearBookingState.data)
        }
    }
}

@Composable
private fun DashedLine(){
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val color = FefuFitTheme.color.textColor.mainTextColor
    Canvas(
        Modifier.fillMaxHeight()
            ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            pathEffect = pathEffect
        )
    }
}

//Vertical modifier
fun Modifier.rotateVertically(rotation: VerticalRotation) = then(
    object : LayoutModifier {
        override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
            val placeable = measurable.measure(constraints)
            return layout(placeable.height, placeable.width) {
                placeable.place(
                    x = -(placeable.width / 2 - placeable.height / 2),
                    y = -(placeable.height / 2 - placeable.width / 2)
                )
            }
        }

        override fun IntrinsicMeasureScope.minIntrinsicHeight(measurable: IntrinsicMeasurable, width: Int): Int {
            return measurable.maxIntrinsicWidth(width)
        }

        override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurable: IntrinsicMeasurable, width: Int): Int {
            return measurable.maxIntrinsicWidth(width)
        }

        override fun IntrinsicMeasureScope.minIntrinsicWidth(measurable: IntrinsicMeasurable, height: Int): Int {
            return measurable.minIntrinsicHeight(height)
        }

        override fun IntrinsicMeasureScope.maxIntrinsicWidth(measurable: IntrinsicMeasurable, height: Int): Int {
            return measurable.maxIntrinsicHeight(height)
        }
    })
    .then(rotate(rotation.value))

enum class VerticalRotation(val value: Float) {
    CLOCKWISE(90f), COUNTER_CLOCKWISE(270f)
}



@Composable
fun NearEventCard(nearBookingData:UserBookingDataModelItem) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = FefuFitTheme.color.mainAppColors.appCardColor
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .width(280.dp)
                        .padding(15.dp)
                ) {
                    Text(
                        text = nearBookingData.eventName,
                        fontSize = 17.sp,
                        fontWeight = FontWeight(500),
                        color = FefuFitTheme.color.textColor.mainTextColor,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Сегодня, 14:00 - 16:00",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = FefuFitTheme.color.textColor.secondaryTextColor,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.geo_pos_icon),
                            contentDescription = "geo_icon",
                            tint = FefuFitTheme.color.textColor.mainTextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = nearBookingData.buildingName,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(300),
                            color = FefuFitTheme.color.textColor.mainTextColor,
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_icon),
                            contentDescription = "geo_icon",
                            tint = FefuFitTheme.color.textColor.mainTextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = nearBookingData.coachName,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(300),
                            color = FefuFitTheme.color.textColor.mainTextColor,
                        )
                    }
                }

            }
            Row() {
                DashedLine()
                TextButton(
                    modifier = Modifier
                        .rotateVertically(VerticalRotation.COUNTER_CLOCKWISE)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    ),
                    onClick = {}
                ) {
                    Text(
                        modifier = Modifier,
                        text = "отменить",
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(300),
                        color = FefuFitTheme.color.textColor.mainTextColor,
                    )
                }
            }
        }
    }
}


@Composable
private fun QrCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
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
private fun MainMenuUppBar(
    userDataState: UserDataState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
       if (!userDataState.isLoading){
           Text(
               modifier = Modifier.fillMaxWidth(0.7f),
               text = buildAnnotatedString {
                   append("Время тренировки, ")
                   withStyle(style = SpanStyle(fontWeight = FontWeight(300))) {
                       if (userDataState.data != null){
                           append("${userDataState.data.firstName}!")
                       }
                       else{
                           append("Ошибка!")
                       }
                   }
               },
               fontSize = 22.sp,
               fontWeight = FontWeight(500),
               color  = FefuFitTheme.color.textColor.mainTextColor,
           )
       }
       else{
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = FefuFitTheme.color.elementsColor.elementColor
                )
            }
       }
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
                    painter = painterResource(id = R.drawable.person_icon),
                    contentDescription = "person",
                    tint = FefuFitTheme.color.elementsColor.elementColor
                )
            }
        }
    }
}


