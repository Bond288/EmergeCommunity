package com.glootie.networking.auth.data

import com.facebook.AccessToken

class FaceBookAuthDataSource {

    var isLoggedIn = AccessToken.getCurrentAccessToken()?.isExpired ?: false
}