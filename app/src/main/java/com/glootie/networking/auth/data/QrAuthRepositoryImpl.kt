package com.glootie.networking.auth.data

import com.glootie.networking.auth.domain.QrAuthRepository
import javax.inject.Inject

class QrAuthRepositoryImpl @Inject constructor(
    private val qrAuhDataSource: QRAuhDataSource
): QrAuthRepository {

    override fun isAuthorizen(): Boolean {
        return qrAuhDataSource.isAuth()
    }

    override fun setAuthCompleted() {
        qrAuhDataSource.onAuth()
    }
}