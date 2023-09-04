package com.example.main_impl.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.theme.FefuFitTheme
import com.example.main_impl.domain.models.UserBookingDataModelItem
import com.example.main_impl.domain.models.UserServicesDataModelItem
import com.example.main_impl.presentation.models.ActiveServicesState
import com.example.main_impl.presentation.models.NearBookingDataState
import com.example.main_impl.presentation.models.UserDataState
import com.example.main_page_impl.R
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.main_impl.presentation.elements.QrCodeDialog
import com.example.main_impl.presentation.models.QrCodeState
import com.google.accompanist.pager.HorizontalPagerIndicator


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,)
@Composable
fun MainMenuScreen(
    viewModel:MainMenuViewModel = hiltViewModel()
) {
    //data states
    val userDataState = viewModel.userDataState.value
    val nearBookingState = viewModel.nearBookingState.value
    val activeUserServicesState = viewModel.activeServicesState.value
    val qrCodeState = viewModel.qrCodeState.value

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
            QrCard(qrCodeState)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ActiveServicesSpace(
    pagerState: PagerState,
    activeUserServicesState: ActiveServicesState
) {

    val emptyCardModifier = Modifier
        .height(176.dp)
        .padding(horizontal = 16.dp)

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

        ShimmerCardHolder(
            isLoading = activeUserServicesState.isLoading,
            modifier = emptyCardModifier
        ) {
            if (activeUserServicesState.error != null)
                EmptyCard(
                    modifier = emptyCardModifier,
                    text = activeUserServicesState.error
                )
            else if (activeUserServicesState.data == null) {
                EmptyCard(
                    modifier = emptyCardModifier,
                    text = "Активных абонементов нет"
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPager(
                        modifier = Modifier.fillMaxWidth(),
                        pageCount = activeUserServicesState.data.size,
                        state = pagerState
                    ) { id ->
                        ActiveServicesCard(activeUserServicesState.data[id])
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        pageCount =  activeUserServicesState.data.size,
                        activeColor = FefuFitTheme.color.elementsColor.elementColor
                    )
                }
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
            .size(39.dp)
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
                        .fillMaxWidth(0.90f)
                        .padding(15.dp)
                ) {
                    Text(
                        text = data.serviceName,
                        fontSize = 17.sp,
                        fontWeight = FontWeight(500),
                        color = FefuFitTheme.color.textColor.mainTextColor,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        for (i in 1..data.planCapacity){
                            val visit = i <= data.eventsDone
                            ServiceCircle(i, visit)
                            Spacer(modifier = Modifier
                                .height(44.dp))
                        }
                    }
                }
            }
            Row() {
                DashedLine()
                TextButton(
                    enabled = false,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(44.dp),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 0.dp
                    ),
                    onClick = {}
                ) {
                }
            }
            Text(
                modifier = Modifier
                    .rotateVertically(VerticalRotation.COUNTER_CLOCKWISE)
                    .padding(bottom = 12.dp),
                text = "до ${data.expDate}",
                maxLines = 1,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight(300),
                color = FefuFitTheme.color.textColor.secondaryTextColor,
            )
        }
    }
}

@Composable
private fun ShimmerCardHolder(
    isLoading:Boolean,
    modifier: Modifier = Modifier,
    contentAfterLoading: @Composable () -> Unit,
){
    if (isLoading){
        ShimmerCard(modifier = modifier)
    }
    else
        contentAfterLoading()
}

fun Modifier.shimmerEffect(shape: Shape): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1100
            )
        ),
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                FefuFitTheme.color.mainAppColors.appCardColor,
                FefuFitTheme.color.elementsColor.shimmerColor,
                FefuFitTheme.color.mainAppColors.appCardColor,
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        ),
        shape = shape
    ).onGloballyPositioned {
        size = it.size
    }
}

@Composable
private fun ShimmerCard(modifier: Modifier){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shimmerEffect(RoundedCornerShape(16.dp))
    )
}

@Composable
private fun EmptyCard(
    modifier: Modifier,
    text: String
){
    Card(
        modifier = modifier,
//            .padding(horizontal = 18.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = FefuFitTheme.color.mainAppColors.appCardColor
        )
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight(300),
                color = FefuFitTheme.color.textColor.mainTextColor,
            )
        }
    }
}

@Composable
fun NearEventSpace(nearBookingState: NearBookingDataState) {

    val emptyCardModifier = Modifier
        .height(147.dp)
        .padding(horizontal = 18.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ближайшее занятие",
                fontSize = 22.sp,
                fontWeight = FontWeight(400),
                color = FefuFitTheme.color.textColor.mainTextColor,
            )
            TextButton(onClick = { /*TODO*/ }) {
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp),
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
        Spacer(modifier = Modifier.height(10.dp))
        ShimmerCardHolder(
            isLoading = nearBookingState.isLoading,
            modifier = emptyCardModifier
        ) {
            if (nearBookingState.error != null)
            EmptyCard(
                modifier = emptyCardModifier,
                text = nearBookingState.error
            )
            else if (nearBookingState.data == null){
                EmptyCard(
                    modifier = emptyCardModifier,
                    text = "Ближайших занятий нет"
                )
            }
            else{
                NearEventCard(nearBookingState.data)
            }
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
fun NearEventCard(nearBookingData: UserBookingDataModelItem) {
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
                        text = "${nearBookingData.beginData}, ${nearBookingData.beginTime} - ${nearBookingData.endTime}",
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
                            contentDescription = "coach_icon",
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
                    onClick = {}
                ) {

                }
            }
            Text(
                modifier = Modifier
                    .rotateVertically(VerticalRotation.COUNTER_CLOCKWISE)
                    .padding(bottom = 12.dp),
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


@Composable
private fun QrCard(qrCodeState: QrCodeState) {
    val interactionSource = remember { MutableInteractionSource() }
    val isClicked = remember { mutableStateOf(false) }

    if(isClicked.value)
        QrCodeDialog(isClicked, qrCodeState.data!!)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(radius = 400.dp),
                onClick = {
                    isClicked.value = !isClicked.value
                },
            ),
        colors = CardDefaults.cardColors(
            containerColor = FefuFitTheme.color.mainAppColors.appBlueColor,
        ),
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
            Spacer(modifier = Modifier.width(25.dp))
            if (qrCodeState.data != null) {
                Image(
                    bitmap = qrCodeState.data.asImageBitmap(),
                    contentDescription = "qrCode",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Composable
private fun EmptyPhotoIcon(){
    Icon(
        painter = painterResource(id = R.drawable.person_icon),
        contentDescription = "person",
        tint = FefuFitTheme.color.elementsColor.elementColor
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun MainMenuUppBar(
    userDataState: UserDataState
) {
    val userPhoto = rememberImagePainter(
        data = userDataState.data?.photo,
    )

    val emptyCardModifier = Modifier
        .height(56.dp)
        .fillMaxWidth(0.7f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ShimmerCardHolder(
            isLoading = userDataState.isLoading,
            modifier = emptyCardModifier
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = buildAnnotatedString {
                    append("Время тренировки, ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight(300))) {
                        append("${userDataState.data?.firstName}!")
                    }
                },
                fontSize = 22.sp,
                fontWeight = FontWeight(500),
                color  = FefuFitTheme.color.textColor.mainTextColor,
            )
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
                if (userDataState.isLoading || userPhoto.state is ImagePainter.State.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = FefuFitTheme.color.elementsColor.elementColor,
                        strokeWidth = 2.dp
                    )
                } else if (userDataState.data?.photo == null) {
                    EmptyPhotoIcon()
                } else {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape),
                        painter = userPhoto,
                        contentDescription = "person",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
