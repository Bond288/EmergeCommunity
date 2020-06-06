package com.glootie.networking.base.utills

import com.glootie.networking.base.mvvm.BaseViewModel
import io.reactivex.disposables.Disposable

fun Disposable.addTo(baseViewModel: BaseViewModel) {
    baseViewModel.add(this)
}