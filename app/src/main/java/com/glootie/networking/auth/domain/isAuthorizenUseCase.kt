package com.glootie.networking.auth.domain

import javax.inject.Inject

class isAuthorizenUseCase @Inject constructor(private val facebookAuthRepository: FacebookAuthRepository) {

    operator fun invoke(): Boolean = facebookAuthRepository.isAuthorizen()
}