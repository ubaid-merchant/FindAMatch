package com.ubaidmerchant.findamatch.model

data class LocationModel(
    val street: StreetModel,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int,
    val coordinates: CoordinatesModel,
    val timezone: TimezoneModel
)