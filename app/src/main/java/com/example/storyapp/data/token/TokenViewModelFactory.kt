package com.example.storyapp.data.token

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TokenViewModelFactory(private val tokenPref: TokenPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TokenViewModel::class.java)) {
            return TokenViewModel(tokenPref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}