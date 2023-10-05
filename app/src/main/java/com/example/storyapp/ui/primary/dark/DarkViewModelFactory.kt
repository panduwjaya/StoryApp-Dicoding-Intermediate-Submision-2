package com.example.storyapp.ui.primary.dark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DarkViewModelFactory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DarkViewModel::class.java)) {
            return DarkViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}