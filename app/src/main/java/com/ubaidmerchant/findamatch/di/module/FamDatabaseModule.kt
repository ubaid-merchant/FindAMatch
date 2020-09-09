package com.ubaidmerchant.findamatch.di.module

import android.app.Application
import com.ubaidmerchant.findamatch.data.local.FamResultsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class FamDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = FamResultsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: FamResultsDatabase) = database.getResultsDao()
}