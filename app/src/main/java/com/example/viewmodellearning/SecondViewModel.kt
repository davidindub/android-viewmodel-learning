package com.example.viewmodellearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel(private var passedClicks: Int): ViewModel() {
//setup live data
    private var _clicks = MutableLiveData<Int>()
    val clicks: LiveData<Int> get() = _clicks

    init {
        _clicks.value = passedClicks + 1
    }

    public fun addClick() {
        _clicks.value = _clicks.value?.plus(1)
    }
}