package com.glootie.networking.startup.di

import com.glootie.networking.startup.data.StartupRepositoryImpl
import com.glootie.networking.startup.domain.StartupRepository
import toothpick.config.Module

class StartupListModule : Module() {

        init {
            bind(StartupRepository::class.java)
                .toInstance(StartupRepositoryImpl())
        }
}
