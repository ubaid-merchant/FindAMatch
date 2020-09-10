package com.ubaidmerchant.findamatch.model

data class NameModel(
    val title: String?,
    val first: String?,
    val last: String?
) {
    fun getName(): String {
        return title + " " + first + " " + last
    }
}