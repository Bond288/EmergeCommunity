package com.glootie.networking.auth.presentation

import com.glootie.networking.Router
import com.glootie.networking.Screen
import com.glootie.networking.auth.domain.IsQrAuthUseCase
import com.glootie.networking.base.mvvm.BaseViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val qrAuthUseCase: IsQrAuthUseCase,
    private val router: Router
): BaseViewModel() {

    fun onAuthCompleted() {
        qrAuthUseCase()
        router.routeTo(Screen.MAIN)
    }
}