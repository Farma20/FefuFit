package com.example.fefufit.glue.timetable_screen.mappers

import com.example.fefufit.glue.main_screen.mappers.DateFormat
import com.example.remote.models.events_data_models.DataEventDataModel
import com.example.timetable_impl.domain.models.EventDataModel
import com.example.timetable_impl.domain.models.EventDataModelItem
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun DataEventDataModel.toEventDataModel(): EventDataModel{
    val data = EventDataModel()
    for (item in this){
        data.add(
            EventDataModelItem(
                id = item.id,
                eventName = item.eventName,
                buildingName = item.buildingName,
                coachEmail = item.coachEmail,
                coachName = item.coachName,
                coachPhoneNumber = item.coachPhoneNumber,
                beginTime = formatTime(item.beginTime),
                endTime = formatTime(item.endTime),
                occupiedSpaces = item.occupiedSpaces,
                totalSpaces = item.totalSpaces,
                day = LocalDate.parse(item.beginTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                areaName = item.areaName,
                status = item.status,
            )
        )
    }
    return data
}

private fun formatDate(date:String, format:DateFormat):String{
    val myDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))

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

