package com.example.storyapp.ui.primary.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.repo.StoryRepository
import com.example.storyapp.data.response.detail.DetailResponse
import kotlinx.coroutines.launch

class DetailViewModel(private val storyRepository: StoryRepository): ViewModel(){
    fun getDetailStory(id: String) = storyRepository.getDetailStory(id)
}