package com.example.main_impl.domain.use_cases

import com.example.common.Resource
import com.example.main_impl.domain.models.UserBookingDataModelItem
import com.example.main_impl.domain.repositories.EventsFeatureRepository
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserNearBookingUseCase @Inject constructor(
    private val repository: EventsFeatureRepository,
    private val metaDataRepository: MainMetaDataRepository
) {
    operator fun invoke(): Flow<Resource<UserBookingDataModelItem?>> = flow {
        try {
            emit(Resource.Loading())
            val userToken = metaDataRepository.getUserTokenMetaData()
            val bookingData = repository.getAllUserBookings(userToken)
            if (bookingData.isNotEmpty())
                emit(Resource.Success(bookingData[0]))
            else
                emit(Resource.Success(null))
        }
        catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}


//TODO DELETE

//when (cause) {
//    is HttpException -> {
//        val result = JSONObject(cause.response()?.errorBody()?.string().toString()).toMap()
//        emit(Resource.Error(result["detail"].toString()))
//    }
//    is NullPointerException -> {
//        val errorText = "User token is null"
//        emit(Resource.Error(errorText))
//    }
//    else -> emit(Resource.Error("Unknown error"))
//}