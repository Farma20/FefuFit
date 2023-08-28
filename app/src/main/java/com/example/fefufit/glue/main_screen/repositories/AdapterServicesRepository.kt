package com.example.fefufit.glue.main_screen.repositories

import com.example.main_impl.domain.models.UserServicesDataModel
import com.example.main_impl.domain.models.UserServicesDataModelItem
import com.example.main_impl.domain.repositories.ServicesFeatureRepository
import com.example.remote.ServicesDataRepository
import javax.inject.Inject

class AdapterServicesRepository @Inject constructor(
    private val servicesDataRepository: ServicesDataRepository
):ServicesFeatureRepository {
    override suspend fun getUserServices(token: String): UserServicesDataModel {
        val result = servicesDataRepository.getUserServices(token)
        val data = UserServicesDataModel()
        for (item in result){
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
}