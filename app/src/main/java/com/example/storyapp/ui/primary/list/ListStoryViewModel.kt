package com.example.storyapp.ui.primary.list

import androidx.lifecycle.ViewModel
import com.example.storyapp.data.repo.StoryRepository

class ListStoryViewModel(private val storyRepository: StoryRepository): ViewModel(){
    fun getListStory() = storyRepository.getListStory()
}