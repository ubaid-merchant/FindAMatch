package com.ubaidmerchant.findamatch.model

import androidx.room.Embedded

data class LocationModel(
    @field:Embedded(prefix = "street")
    val street: StreetModel?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    @field:Embedded(prefix = "coordinates")
    val coordinates: CoordinatesModel?,
    @field:Embedded(prefix = "timezone")
    val timezone: TimezoneModel?
) {
    fun getDescription(): String {
        return street?.number.toString() + "," + street?.name + ",\n" + city + "," + state + "," + country + "," + state + "," + postcode
    }
}