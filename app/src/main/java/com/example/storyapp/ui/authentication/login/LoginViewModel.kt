package com.example.storyapp.ui.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.repo.StoryRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val storyRepository: StoryRepository): ViewModel(){
    fun postLogin(email: String, password: String) = storyRepository.postLogin(email,password)
}