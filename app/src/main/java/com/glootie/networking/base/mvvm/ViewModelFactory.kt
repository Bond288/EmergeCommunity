package com.glootie.networking.base.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import toothpick.Scope

class ViewModelFactory(private val scope: Scope) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        scope.getInstance(modelClass)
}