package com.example.fefufit.Domain.Models.ValidationModels

data class SingUpFirstFormState(
    val secondName:String = "",
    val secondNameError:String? = null,
    val firstName:String = "",
    val firstNameError:String? = null,
    val middleName:String = "",
    val middleNameError:String? = null,
    val gender:String = "",
    val genderError:String? = null,
    val birthday:String = "",
    val birthdayError:String? = null,
    val status:String = "",
    val statusError:String? = null
)
