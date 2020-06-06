package com.glootie.networking.startup.presentation

import androidx.lifecycle.MutableLiveData
import com.glootie.networking.base.UiState
import com.glootie.networking.base.mvvm.BaseViewModel
import com.glootie.networking.base.utills.addTo
import com.glootie.networking.startup.domain.GetStartupListUseCase
import com.glootie.networking.startup.domain.model.StartupInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StartupListViewModel @Inject constructor(
    getStartupListUseCase: GetStartupListUseCase
) : BaseViewModel() {

    val startupList = MutableLiveData<List<StartupInfo>>()
    val state = MutableLiveData<UiState>()

    init {
        state.value = UiState.PROGRESS
        getStartupListUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                startupList.value = it
                state.value = UiState.SUCCESS
            },{
                it.stackTrace
                state.value = UiState.FAIL
            }).addTo(this)
    }

    fun onRemove(currentItem: Int?) {
        currentItem ?: return
        val items = startupList.value ?: return
        startupList.value = items.filterNot { it == items[currentItem] }
    }

    fun onApprove(currentItem: Int?) {

    }
}