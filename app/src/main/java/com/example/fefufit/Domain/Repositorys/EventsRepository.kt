package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Remote.Models.EventsData.UserBookingDataModel

interface EventsRepository {
    suspend fun getAllUserBookings():UserBookingDataModel
}