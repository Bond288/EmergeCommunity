package com.glootie.networking.startup.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.glootie.networking.startup.domain.model.StartupInfo
import com.glootie.networking.startup.item.ui.StartupFragment

class StartupFragmentAdapter(
    parentFragment: Fragment
) : FragmentStateAdapter(parentFragment) {

    var startupList: List<StartupInfo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = startupList.size

    override fun createFragment(position: Int): Fragment {
        val startup = startupList[position]
        return StartupFragment.create(startup)
    }
}