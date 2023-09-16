package com.example.timetable_impl.presentation.timetable_screen.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
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
import androidx.ui.tooling.preview.Preview
import com.example.core.theme.FefuFitTheme
import com.example.timetable_impl.R

@Composable
fun EventCard() {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = FefuFitTheme.color.mainAppColors.appCardColor
        ),
        shape = RoundedCornerShape(16.dp)
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
                        text = "Настольный теннис",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = FefuFitTheme.color.textColor.mainTextColor,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "14:00 - 16:00",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = FefuFitTheme.color.textColor.secondaryTextColor,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
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
                            text = "Фитнес-центр ГК11, зал групповых программ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(300),
                            color = FefuFitTheme.color.textColor.mainTextColor,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_icon),
                            contentDescription = "coach_icon",
                            tint = FefuFitTheme.color.textColor.mainTextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Шукурова Карина Андреевна",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(300),
                            color = FefuFitTheme.color.textColor.mainTextColor,
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.padding(start = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("Свободно")
                                withStyle(style = SpanStyle(color = FefuFitTheme.color.textColor.secondaryTextColor)){
                                    append(" 13 мест ")
                                }
                                append("из 25")
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = FefuFitTheme.color.textColor.mainTextColor,
                        )
                    }
                }

            }
            Row() {
//                DashedLine()
                TextButton(
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(44.dp),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 0.dp
                    ),
                    onClick = {},
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = FefuFitTheme.color.mainAppColors.appBlueColor
                    )
                ) {

                }
            }
            Text(
                modifier = Modifier
                    .rotateVertically(VerticalRotation.COUNTER_CLOCKWISE)
                    .padding(bottom = 12.dp),
                text = "записаться",
                maxLines = 1,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight(300),
                color = FefuFitTheme.color.textColor.whiteTextColor,
            )
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

@Preview
@Composable
private fun PreviewEventCard(){
    EventCard()
}