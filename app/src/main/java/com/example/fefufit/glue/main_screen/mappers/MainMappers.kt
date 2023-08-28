package com.example.fefufit.glue.main_screen.mappers

import com.example.fefufit.data.remote.models.events_data_models.DataUserBookingDataModel
import com.example.fefufit.data.remote.models.services_data_models.DataUserServicesDataModel
import com.example.fefufit.data.remote.models.user_data_models.DataUserDataModel
import com.example.main_impl.domain.models.UserBookingDataModel
import com.example.main_impl.domain.models.UserBookingDataModelItem
import com.example.main_impl.domain.models.UserDataModel
import com.example.main_impl.domain.models.UserServicesDataModel
import com.example.main_impl.domain.models.UserServicesDataModelItem

fun DataUserBookingDataModel.toUserBookingDataModel():UserBookingDataModel{
    val data = UserBookingDataModel()
    for (item in this){
        data.add(
            UserBookingDataModelItem(
                item.beginTime,
                item.bookingStatus,
                item.buildingName,
                item.coachEmail,
                item.coachName,
                item.coachPhoneNumber,
                item.endTime,
                item.eventName,
                item.id,
                item.occupiedSpaces,
                item.serviceCost,
                item.totalSpaces
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