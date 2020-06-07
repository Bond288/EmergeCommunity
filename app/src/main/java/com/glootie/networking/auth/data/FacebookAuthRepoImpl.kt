package com.glootie.networking.auth.data

import com.glootie.networking.auth.domain.FacebookAuthRepository
import javax.inject.Inject


class FacebookAuthRepoImpl @Inject constructor(
    private val faceBookAuthDataSource: FaceBookAuthDataSource
) : FacebookAuthRepository {

    override fun isAuthorizen(): Boolean = faceBookAuthDataSource.isLoggedIn
}