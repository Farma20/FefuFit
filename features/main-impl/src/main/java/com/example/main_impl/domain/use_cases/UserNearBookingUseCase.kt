package com.example.main_impl.domain.use_cases

import com.example.common.Resource
import com.example.main_impl.domain.models.UserBookingDataModel
import com.example.main_impl.domain.models.UserBookingDataModelItem
import com.example.main_impl.domain.models.UserServicesDataModel
import com.example.main_impl.domain.repositories.EventsFeatureRepository
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserNearBookingUseCase @Inject constructor(
    private val repository: EventsFeatureRepository,
    private val metaDataRepository: MainMetaDataRepository,
    private val refreshUseCase: RefreshUseCase
) {
    operator fun invoke(): Flow<Resource<UserBookingDataModelItem?>> = flow {
        try {
            emit(Resource.Loading())
            val bookingData = getData()
            if (bookingData.isNotEmpty())
                emit(Resource.Success(bookingData[0]))
            else
                emit(Resource.Success(null))
        }
        catch (e: Exception){
            if (e.message.toString() == "token is invalid"){
                refreshUseCase()
                val bookingData = getData()
                if (bookingData.isNotEmpty())
                    emit(Resource.Success(bookingData[0]))
                else
                    emit(Resource.Success(null))
            }
            else{
                emit(Resource.Error(e.message.toString()))
            }
            emit(Resource.Error(e.message.toString()))
        }
    }

    private suspend fun getData(): UserBookingDataModel {
        val userToken = metaDataRepository.getUserTokenMetaData()
        return repository.getAllUserBookings(userToken)
    }
}