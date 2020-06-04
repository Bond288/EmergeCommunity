package com.glootie.networking.base

import android.app.Application
import toothpick.Toothpick

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Toothpick.openScope(this)
    }
}