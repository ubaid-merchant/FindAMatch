package com.ubaidmerchant.findamatch.data.remote.api

import com.ubaidmerchant.findamatch.data.remote.api.FamService.Companion.FAM_RESULTS_API_URL
import com.ubaidmerchant.findamatch.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to fetch Fam results using dummy end point [FAM_RESULTS_API_URL].
 */
interface FamService {

    @GET("/api/?")
    suspend fun getResults(@Query("results") count: Int): Response<ResponseModel>

    companion object {
        const val FAM_RESULTS_API_URL = "https://randomuser.me/"
        const val RESULTS_COUNT = 10
    }
}