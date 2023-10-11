package com.example.storyapp.data.token

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TokenViewModel(private val tokenPref: TokenPreference): ViewModel() {
    fun saveToken(token: String,name: String,userId: String){
        viewModelScope.launch {
            tokenPref.saveToken(token,name,userId)
        }
    }

    fun readToken(): LiveData<String>{
        return tokenPref.readToken().asLiveData()
    }

    fun readName(): LiveData<String>{
        return tokenPref.readName().asLiveData()
    }

    fun readUserId(): LiveData<String>{
        return tokenPref.readUserId().asLiveData()
    }

    fun removeToken(){
        viewModelScope.launch {
            tokenPref.removeToken()
        }
    }
}