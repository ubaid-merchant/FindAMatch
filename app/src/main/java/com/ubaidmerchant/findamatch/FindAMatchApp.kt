package com.ubaidmerchant.findamatch

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.ubaidmerchant.findamatch.utils.isNight

class FindAMatchApp() : Application() {
    override fun onCreate() {
        super.onCreate()

        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}