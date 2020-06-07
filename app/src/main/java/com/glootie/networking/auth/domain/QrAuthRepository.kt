package com.glootie.networking.auth.domain

interface QrAuthRepository {

    fun isAuthorizen() : Boolean

    fun setAuthCompleted()
}