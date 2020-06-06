package com.glootie.networking.startup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glootie.networking.R
import com.glootie.networking.base.BaseFragment
import com.glootie.networking.base.UiState
import com.glootie.networking.base.utills.setVisible
import com.glootie.networking.startup.di.StartupListModule
import com.glootie.networking.startup.presentation.StartupListViewModel
import kotlinx.android.synthetic.main.fragment_startup_list.view.*
import toothpick.config.Module

class StartupListFragment : BaseFragment() {

    private lateinit var startupListViewModel: StartupListViewModel
    private lateinit var adapter: StartupFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_startup_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view.startup_list.isUserInputEnabled = false
        adapter = StartupFragmentAdapter(this)
        view.startup_list.adapter = adapter
        startupListViewModel = getViewModel()
        observeViewModel()
    }

    private fun observeViewModel() {
        startupListViewModel.startupList.observe(this::getLifecycle, adapter::startupList::set)
        startupListViewModel.state.observe(this::getLifecycle, ::onChangeState)
    }

    private fun onChangeState(state: UiState) {
        view?.startup_list?.setVisible(state == UiState.SUCCESS)
        view?.progress?.setVisible(state == UiState.PROGRESS)
        view?.fail?.setVisible(state == UiState.FAIL)
    }

    fun onSwipeLeft() {
        startupListViewModel.onRemove(view?.startup_list?.currentItem)
    }

    fun onSwipeRight() {
        startupListViewModel.onApprove(view?.startup_list?.currentItem)
    }

    override fun getModules(): Array<Module> = arrayOf(StartupListModule())
}