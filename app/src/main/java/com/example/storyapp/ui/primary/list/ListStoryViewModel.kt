package com.example.storyapp.ui.primary.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.storyapp.data.repo.StoryRepository
import com.example.storyapp.data.response.list.ListStory

class ListStoryViewModel(storyRepository: StoryRepository): ViewModel(){

    val getListStory: LiveData<PagingData<ListStory>> = storyRepository.getListStory().cachedIn(viewModelScope)
}