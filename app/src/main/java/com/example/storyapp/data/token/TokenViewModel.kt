package com.example.storyapp.data.token

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TokenViewModel(private val tokenPref: TokenPreference): ViewModel() {
    fun saveToken(token: String){
        viewModelScope.launch {
            tokenPref.saveToken(token)
        }
    }

    fun readToken(): LiveData<String>{
        return tokenPref.readToken().asLiveData()
    }

    fun removeToken(){
        viewModelScope.launch {
            tokenPref.removeToken()
        }
    }
}