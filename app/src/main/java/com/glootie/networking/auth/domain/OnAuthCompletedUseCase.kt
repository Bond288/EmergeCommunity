package com.glootie.networking.auth.domain

import javax.inject.Inject

class OnAuthCompletedUseCase @Inject constructor(
    private val qrAuthRepository: QrAuthRepository
) {
    operator  fun invoke() {
        qrAuthRepository.setAuthCompleted()
    }
}