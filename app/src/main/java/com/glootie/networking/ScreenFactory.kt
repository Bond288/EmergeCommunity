package com.glootie.networking

import com.glootie.networking.auth.ui.AuthFragment
import com.glootie.networking.base.BaseFragment
import com.glootie.networking.startup.ui.StartupListFragment

class ScreenFactory {

    fun screen(screen: Screen): BaseFragment =
        when(screen){
            Screen.AUTH -> AuthFragment()
            Screen.MAIN -> StartupListFragment()
        }
}