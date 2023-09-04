package com.example.main_impl.domain.use_cases

import android.graphics.Bitmap
import com.example.common.Resource
import com.example.main_impl.domain.models.UserShortDataModel
import com.example.main_impl.domain.models.toShort
import com.example.main_impl.domain.repositories.MainMetaDataRepository
import com.example.main_impl.utils.QrCodeGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenerateQrCodeUseCase @Inject constructor(
    private val metaDataRepository: MainMetaDataRepository,
    private val qrCodeGenerator: QrCodeGenerator
) {
    operator fun invoke(): Flow<Resource<Bitmap>> = flow {
        try {
            emit(Resource.Loading())
            val userToken = metaDataRepository.getUserTokenMetaData()
            val qrCode = qrCodeGenerator.generate(userToken)
            emit(Resource.Success(qrCode))
        }
        catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}