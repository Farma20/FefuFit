package com.example.fefufit.glue.main_screen.mappers

import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel
import com.example.fefufit.data.remote.models.user_data_models.DataUserDataModel
import com.example.main_impl.domain.models.UserBookingDataModel
import com.example.main_impl.domain.models.UserBookingDataModelItem
import com.example.main_impl.domain.models.UserDataModel
import com.example.main_impl.domain.models.UserServicesDataModel
import com.example.main_impl.domain.models.UserServicesDataModelItem
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val NOW_DAY = "Сегодня"
private const val TOMORROW_DAY = "Завтра"

private fun formatDate(date:String, format:DateFormat):String{
    val currentDate = LocalDate.now()

    val myDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))

    when(myDate){
        currentDate -> return NOW_DAY
        currentDate.plusDays(1) -> return TOMORROW_DAY
    }

    val dateFormat = when (format){
        DateFormat.MonthIsNumbers -> "dd.MM.yyyy"
        DateFormat.MonthIsWords -> "d MMMM"
    }
    val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale("ru"))
    val formattedDate = myDate.format(formatter)

    return formattedDate.toString()
}

private fun formatTime(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val myDate = LocalDateTime.parse(date, formatter)
    val time = myDate.format(DateTimeFormatter.ofPattern("HH:mm"))
    return time.toString()
}

fun DataUserBookingDataModel.toUserBookingDataModel():UserBookingDataModel{
    val data = UserBookingDataModel()
    for (item in this){
        data.add(
            UserBookingDataModelItem(
                id = item.id,
                eventName = item.eventName,
                buildingName = item.buildingName,
                coachEmail = item.coachEmail,
                coachName = item.coachName,
                coachPhoneNumber = item.coachPhoneNumber,
                beginData = formatDate(item.beginTime, DateFormat.MonthIsWords),
                beginTime = formatTime(item.beginTime),
                endTime = formatTime(item.endTime),
                bookingStatus = item.bookingStatus,
                serviceCost = item.serviceCost,
                occupiedSpaces = item.occupiedSpaces,
                totalSpaces = item.totalSpaces
            )
        )
    }
    return data
}

fun DataUserServicesDataModel.toUserServicesDataModel():UserServicesDataModel{
    val data = UserServicesDataModel()
    for (item in this){
        data.add(
            UserServicesDataModelItem(
                eventsDone = item.eventsDone,
                expDate = item.expDate,
                planCapacity = item.planCapacity,
                planId = item.planId,
                serviceName = item.serviceName
            )
        )
    }
    return data
}

fun DataUserDataModel.toUserDataModel():UserDataModel{
    return UserDataModel(
        this.birthdate,
        this.email,
        this.firstName,
        this.gender,
        this.phoneNumber,
        this.photo,
        this.secondName,
        this.status,
        this.telegramId,
        this.middleName,
        this.type
    )
}

sealed class DateFormat{
    object MonthIsWords:DateFormat()
    object MonthIsNumbers:DateFormat()
}