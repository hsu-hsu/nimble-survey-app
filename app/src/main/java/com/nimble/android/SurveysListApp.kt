package com.nimble.android

import android.app.Application
import com.nimble.android.preference.AppSharedPreferences
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SurveysListApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppSharedPreferences.init(applicationContext)
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}