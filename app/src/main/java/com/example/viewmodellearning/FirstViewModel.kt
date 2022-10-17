package com.example.viewmodellearning

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel(private var passedClicks: Int = 0) : ViewModel() {
//    Using Live Data
//    We'll change the value of the MutableLiveData object
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _clicks = MutableLiveData<Int>()
    val clicks: LiveData<Int> get() = _clicks

    init {
        Log.i("test", "View Model Created!!")
        _message.value = ""
        _clicks.value = passedClicks + 1
    }

    fun addClick() {
        _clicks.value = _clicks.value?.plus(1)
    }

    fun hello(name: String) {
        _message.value = "Hello $name"
        addClick()
    }

    fun goodbye(name: String) {
        _message.value = "Goodbye $name"
        addClick()
    }
}