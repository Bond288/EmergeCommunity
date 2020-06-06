package com.glootie.networking.startup.domain

import com.glootie.networking.startup.domain.model.StartupInfo
import io.reactivex.Single

interface StartupRepository {

    fun get(): Single<List<StartupInfo>>
}