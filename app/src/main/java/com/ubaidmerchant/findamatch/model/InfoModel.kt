package com.ubaidmerchant.findamatch.model

data class InfoModel(
    val seed: String?,
    val results: Int = 0,
    val page: Int = 0,
    val version: String?
)