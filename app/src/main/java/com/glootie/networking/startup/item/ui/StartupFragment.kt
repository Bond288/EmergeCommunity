package com.glootie.networking.startup.item.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glootie.networking.R
import com.glootie.networking.base.BaseFragment
import com.glootie.networking.base.view.StartupLayout
import com.glootie.networking.startup.domain.model.StartupInfo
import com.glootie.networking.startup.ui.StartupListFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_startup.view.*
import kotlinx.android.synthetic.main.fragment_startup_layout.view.*

class StartupFragment : BaseFragment() {

    companion object {

        private const val STARTUP_EXTRA = "STARTUP_EXTRA"

        fun create(startupInfo: StartupInfo): StartupFragment =
            StartupFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(STARTUP_EXTRA, startupInfo)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_startup_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startupInfo = arguments?.getSerializable(STARTUP_EXTRA) as? StartupInfo ?: return
        view.startup_name.text = startupInfo.title
        view.startup_description.text = Html.fromHtml(startupInfo.description)
        BottomSheetBehavior.from(view.bottom_sheet_panel)
        view.root.moveEvent.observe(this::getLifecycle, ::onEvent)
        val player = VideoPlayer(view.context, lifecycle)
        view.scene.useController = false
        player.setDataSource(view.scene!!, startupInfo)
    }

    private fun onEvent(event: StartupLayout.MoveEvent) {
        when (event) {
            StartupLayout.MoveEvent.SWIPE_RIGHT -> (parentFragment as? StartupListFragment)?.onSwipeRight()
            StartupLayout.MoveEvent.SWIPE_LEFT -> (parentFragment as? StartupListFragment)?.onSwipeLeft()
        }
    }
}