package com.glootie.networking.startup.item.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glootie.networking.R
import com.glootie.networking.base.BaseFragment
import com.glootie.networking.base.utills.setVisible
import com.glootie.networking.base.view.StartupLayout
import com.glootie.networking.startup.domain.model.StartupInfo
import com.glootie.networking.startup.domain.model.StartupQuickDescription
import com.glootie.networking.startup.ui.StartupListFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_startup.*
import kotlinx.android.synthetic.main.bottom_sheet_startup.view.*
import kotlinx.android.synthetic.main.bottom_sheet_startup.view.startup_money_info
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

        initializeBottomSheet()
        initializeQuickInfo(startupInfo.quickDescription)

        view.root.moveEvent.observe(this::getLifecycle, ::onEvent)
        val player = VideoPlayer(view.context, lifecycle)
        view.scene.useController = false
        player.setDataSource(view.scene!!, startupInfo)
        button_call_developer.setOnClickListener { callDeveloper(getString(startupInfo.callLinkRes)) }
        startup_image.setImageResource(startupInfo.imageRes)
        startup_money_info.setVisible(startupInfo.moneyInfo != null)
    }

    private fun initializeBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(view!!.bottom_sheet_panel)
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
                // TODO: 07.06.2020 make animation great again
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        quick_description.setVisible(true)
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        quick_description.setVisible(false)
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        quick_description.setVisible(true)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        quick_description.setVisible(true)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })
    }

    private fun initializeQuickInfo(startupQuickDescription: StartupQuickDescription) {
        startup_quick_description.text = startupQuickDescription.description
        startup_quick_money_value.text = getString(startupQuickDescription.moneyValueRes)
        startup_quick_money_how.text = startupQuickDescription.moneyHow
    }

    private fun callDeveloper(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun onEvent(event: StartupLayout.MoveEvent) {
        when (event) {
            StartupLayout.MoveEvent.SWIPE_RIGHT -> (parentFragment as? StartupListFragment)?.onSwipeRight()
            StartupLayout.MoveEvent.SWIPE_LEFT -> (parentFragment as? StartupListFragment)?.onSwipeLeft()
        }
    }
}