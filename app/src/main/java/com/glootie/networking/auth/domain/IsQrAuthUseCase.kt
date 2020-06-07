package com.glootie.networking.auth.domain

import javax.inject.Inject

class IsQrAuthUseCase @Inject constructor(private val qrAuthRepository: QrAuthRepository) {

    operator fun invoke(): Boolean = qrAuthRepository.isAuthorizen()
}