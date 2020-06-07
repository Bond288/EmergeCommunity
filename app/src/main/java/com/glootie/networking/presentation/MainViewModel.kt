package com.glootie.networking.presentation

import androidx.lifecycle.MutableLiveData
import com.glootie.networking.Router
import com.glootie.networking.Screen
import com.glootie.networking.auth.domain.IsQrAuthUseCase
import com.glootie.networking.auth.domain.isAuthorizenUseCase
import com.glootie.networking.base.mvvm.BaseViewModel
import com.glootie.networking.base.utills.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    isQrAuthUseCase: IsQrAuthUseCase,
    isAuthorizenUseCase: isAuthorizenUseCase,
    router: Router
): BaseViewModel() {

    val screen = MutableLiveData<Screen>()

    init {
        screen.value = if (isAuthorizenUseCase() || isQrAuthUseCase()){
            Screen.MAIN
        } else {
            Screen.AUTH
        }

        router.screen
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { screen.value = it }
            .addTo(this)
    }

}