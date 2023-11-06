package com.nimble.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SurveysListApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}