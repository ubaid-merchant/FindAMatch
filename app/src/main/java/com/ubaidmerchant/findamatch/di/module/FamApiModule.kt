package com.ubaidmerchant.findamatch.di.module

import com.ubaidmerchant.findamatch.data.remote.api.FamService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class FamApiModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): FamService = Retrofit.Builder()
        .baseUrl(FamService.FAM_RESULTS_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FamService::class.java)
}