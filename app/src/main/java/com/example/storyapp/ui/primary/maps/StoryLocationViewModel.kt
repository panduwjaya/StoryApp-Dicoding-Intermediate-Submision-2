package com.example.storyapp.ui.primary.maps

import androidx.lifecycle.ViewModel
import com.example.storyapp.data.repo.StoryRepository

class StoryLocationViewModel(private val storyRepository: StoryRepository): ViewModel() {
    fun getStoryLocation(id: Int) = storyRepository.getStoryLocation(id)
}