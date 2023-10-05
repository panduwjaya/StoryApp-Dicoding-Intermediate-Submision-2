package com.example.storyapp.ui.primary.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.repo.StoryRepository
import kotlinx.coroutines.launch

class ListStoryViewModel(private val storyRepository: StoryRepository): ViewModel(){
    fun getListStory() = storyRepository.getStory()
}