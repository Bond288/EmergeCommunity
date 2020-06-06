package com.glootie.networking.startup.domain

import com.glootie.networking.startup.domain.model.StartupInfo
import io.reactivex.Single
import javax.inject.Inject

class GetStartupListUseCase @Inject constructor(private val startupRepository: StartupRepository) {

    operator fun invoke(): Single<List<StartupInfo>> = startupRepository.get()
}