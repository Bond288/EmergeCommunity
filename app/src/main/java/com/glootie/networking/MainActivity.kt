package com.glootie.networking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.glootie.networking.auth.di.MainModule
import com.glootie.networking.base.BaseActivity
import com.glootie.networking.presentation.MainViewModel
import com.glootie.networking.startup.ui.StartupListFragment
import toothpick.config.Module


class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    private val screenFactory = ScreenFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel()
        viewModel.screen.observe(this::getLifecycle, ::showScreen)
    }

    override fun getModules(): Array<Module> = arrayOf(MainModule(this))

    private fun showScreen(screen: Screen){
        val screenFragment = screenFactory.screen(screen)
        supportFragmentManager.beginTransaction()
            .add(R.id.screen, screenFragment)
            .commit()
    }
}