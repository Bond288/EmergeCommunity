package com.glootie.networking.auth.data

import com.facebook.AccessToken
import javax.inject.Inject

class FaceBookAuthDataSource @Inject constructor() {

    var isLoggedIn = AccessToken.getCurrentAccessToken()?.isExpired ?: false
}