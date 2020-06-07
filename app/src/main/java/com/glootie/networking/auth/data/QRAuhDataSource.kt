package com.glootie.networking.auth.data

import android.content.SharedPreferences

class QRAuhDataSource(private val sharedPreferences: SharedPreferences) {

    private  companion object {

        const val QR_AUTH = "QR_AUTH"
    }

    fun onAuth() {
        sharedPreferences.edit()
            .putBoolean(QR_AUTH, true)
            .apply()
    }

    fun isAuth(): Boolean = sharedPreferences.getBoolean(QR_AUTH, false)
}