package com.example.demoapp.views.main

import androidx.lifecycle.LiveData
import com.example.demoapp.utils.mvvm.RxViewModel
import com.example.demoapp.utils.mvvm.SingleLiveEvent

class MainViewModel : RxViewModel() {

    private val _state = SingleLiveEvent<MainState>()
    val state: LiveData<MainState>
        get() = _state

    fun startLoad() {
        _state.value = MainState(isLoading = true)
    }

    fun endLoad() {
        _state.value = MainState(isLoading = false)
    }

}

data class MainState(val isLoading: Boolean = false)