package com.glootie.networking.auth.domain

class isAuthorizenUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(): Boolean = authRepository.isAuthorizen()
}