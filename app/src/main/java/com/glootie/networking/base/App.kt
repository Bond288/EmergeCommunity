package com.glootie.networking.base

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import toothpick.Toothpick

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Toothpick.openScope(this)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}