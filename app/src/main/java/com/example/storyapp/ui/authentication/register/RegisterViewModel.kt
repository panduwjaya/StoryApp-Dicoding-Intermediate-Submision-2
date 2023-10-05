package com.example.storyapp.ui.authentication.register

import androidx.lifecycle.ViewModel
import com.example.storyapp.data.repo.StoryRepository

class RegisterViewModel(private val storyRepository: StoryRepository): ViewModel(){
    fun postRegister(name: String, email: String, password: String) = storyRepository.postRegister(name,email,password)
}