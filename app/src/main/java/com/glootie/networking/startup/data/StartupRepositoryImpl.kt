package com.glootie.networking.startup.data

import com.glootie.networking.startup.domain.StartupRepository
import com.glootie.networking.startup.domain.model.StartupInfo
import io.reactivex.Single

class StartupRepositoryImpl : StartupRepository {

    override fun get(): Single<List<StartupInfo>> = Single.just(DataFactory.STARTUP_LIST)
}