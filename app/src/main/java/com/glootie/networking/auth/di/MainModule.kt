package com.glootie.networking.auth.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.glootie.networking.auth.data.FacebookAuthRepoImpl
import com.glootie.networking.auth.data.QRAuhDataSource
import com.glootie.networking.auth.data.QrAuthRepositoryImpl
import com.glootie.networking.auth.domain.FacebookAuthRepository
import com.glootie.networking.auth.domain.QrAuthRepository
import toothpick.config.Module

class MainModule(context: Context) : Module() {

    private companion object {

        const val QR_PREFERENCE = "QR_PREFERENCE"
    }

    init {
        bind(QRAuhDataSource::class.java)
            .toInstance(QRAuhDataSource(context.getSharedPreferences(QR_PREFERENCE, MODE_PRIVATE)))

        bind(QrAuthRepository::class.java)
            .to(QrAuthRepositoryImpl::class.java)

        bind(FacebookAuthRepository::class.java)
            .to(FacebookAuthRepoImpl::class.java)
    }
}