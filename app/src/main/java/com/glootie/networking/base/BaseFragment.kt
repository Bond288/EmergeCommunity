package com.glootie.networking.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.glootie.networking.base.mvvm.BaseViewModel
import com.glootie.networking.base.mvvm.ViewModelFactory
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

open class BaseFragment : Fragment() {

    lateinit var scope: Scope

    override fun onAttach(context: Context) {
        super.onAttach(context)
        scope = if (parentFragment == null) {
            Toothpick.openScopes(activity!!.application, activity, this)
                .installModules(*getModules())
        } else {
            Toothpick.openScopes(activity!!.application, activity, parentFragment, this)
                .installModules(*getModules())
        }
    }

    protected open fun getModules(): Array<Module> = emptyArray()

    protected inline fun <reified VM : BaseViewModel> getViewModel(): VM =
        ViewModelProvider(this, ViewModelFactory(scope))
            .get(VM::class.java)

    override fun onDetach() {
        super.onDetach()
        Toothpick.closeScope(this)
    }
}