package com.glootie.networking.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.glootie.networking.base.mvvm.BaseViewModel
import com.glootie.networking.base.mvvm.ViewModelFactory
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

open class BaseActivity : AppCompatActivity() {

    lateinit var scope: Scope

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        scope = Toothpick.openScopes(application, this)
            .installModules(*getModules())
    }

    protected open fun getModules(): Array<Module> = emptyArray()

    protected inline fun <reified VM : BaseViewModel> getViewModel(): VM =
        ViewModelProvider(this, ViewModelFactory(scope))
            .get(VM::class.java)

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(this)
    }
}