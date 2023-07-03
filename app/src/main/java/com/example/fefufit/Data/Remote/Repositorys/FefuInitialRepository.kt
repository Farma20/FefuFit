package com.example.fefufit.Data.Remote.Repositorys

import com.example.fefufit.Data.Remote.API.FefuFitApi
import com.example.fefufit.Data.Remote.Models.SingInDataModel
import com.example.fefufit.Data.Remote.Models.SingUpDataModel
import com.example.fefufit.Domain.Repositorys.InitializationRepository
import javax.inject.Inject

class FefuInitialRepository(private val fefuFitApi: FefuFitApi):InitializationRepository {
    override suspend fun singIn(singInData: SingInDataModel) {
        try {
            fefuFitApi.singIn(singInData)
        }catch (e:Exception){
            println(e)
        }
    }

    override suspend fun singUp(singUpData: SingUpDataModel) {
        try {
            fefuFitApi.singUp(singUpData)
        }catch (e:Exception){
            println(e)
        }
    }

}