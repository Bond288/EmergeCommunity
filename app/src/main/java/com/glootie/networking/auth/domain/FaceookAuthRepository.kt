package com.glootie.networking.auth.domain

interface FacebookAuthRepository {

    fun isAuthorizen() : Boolean
}