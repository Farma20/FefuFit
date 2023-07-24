package com.example.fefufit.Domain.Repositorys

import com.example.fefufit.Data.Remote.Models.ServicesDataModels.UserServicesDataModel

interface ServicesRepository {
    suspend fun getUserServices():UserServicesDataModel
}