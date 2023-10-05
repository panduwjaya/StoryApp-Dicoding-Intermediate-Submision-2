package com.example.storyapp.ui.primary.add

import androidx.lifecycle.ViewModel
import com.example.storyapp.data.repo.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoryViewModel(private val storyRepository: StoryRepository): ViewModel(){
    fun postStory(file: MultipartBody.Part, description: RequestBody) = storyRepository.postStory(file,description)
}