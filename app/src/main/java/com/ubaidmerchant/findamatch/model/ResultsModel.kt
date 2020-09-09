package com.ubaidmerchant.findamatch.model

data class ResultsModel(
    val gender: String,
    val name: NameModel,
    val location: LocationModel,
    val email: String,
    val login: LoginModel,
    val dob: RegisteredModel,
    val registered: RegisteredModel,
    val phone: String,
    val cell: String,
    val id: IdModel,
    val picture: PictureModel,
    val nat: String
)