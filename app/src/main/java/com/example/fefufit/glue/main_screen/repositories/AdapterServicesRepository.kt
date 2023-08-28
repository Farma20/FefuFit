package com.example.fefufit.glue.main_screen.repositories

import com.example.fefufit.glue.main_screen.mappers.toUserServicesDataModel
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
        return result.toUserServicesDataModel()
    }
}