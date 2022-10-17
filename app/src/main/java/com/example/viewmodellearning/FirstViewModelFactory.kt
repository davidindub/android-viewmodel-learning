package com.example.viewmodellearning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FirstViewModelFactory(var passedClicks: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {
            return FirstViewModel(passedClicks) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}