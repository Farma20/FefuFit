package com.example.fefufit.glue.initialization.repositories

import com.example.remote.InitializationRepository
import com.example.sing_up_impl.domain.models.SingUpDataModel
import com.example.sing_up_impl.domain.repositories.SingUpFeatureRepository
import javax.inject.Inject

class AdapterSingUpRepository @Inject constructor(
    private val initialRepository: InitializationRepository
): SingUpFeatureRepository {
    override suspend fun singUp(singUpData: SingUpDataModel): Map<String, String> {
        return initialRepository.singUp(
            com.example.fefufit.data.remote.models.initial_data_models.SingUpDataModel(
                singUpData.birthdate,
                singUpData.email,
                singUpData.firstName,
                singUpData.gender,
                singUpData.password,
                singUpData.phoneNumber,
                singUpData.secondName,
                singUpData.status,
                singUpData.thirdName
            )
        )
    }
}