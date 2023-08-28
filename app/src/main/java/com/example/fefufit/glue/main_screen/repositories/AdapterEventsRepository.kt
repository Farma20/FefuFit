package com.example.fefufit.glue.main_screen.repositories

import com.example.main_impl.domain.models.UserBookingDataModel
import com.example.main_impl.domain.models.UserBookingDataModelItem
import com.example.main_impl.domain.repositories.EventsFeatureRepository
import com.example.remote.EventsDataRepository
import javax.inject.Inject

class AdapterEventsRepository @Inject constructor(
    private val eventsDataRepository: EventsDataRepository
):EventsFeatureRepository {
    override suspend fun getAllUserBookings(token: String): UserBookingDataModel {
        val result = eventsDataRepository.getAllUserBookings(token)
        val data = UserBookingDataModel()
        for (item in result){
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

}