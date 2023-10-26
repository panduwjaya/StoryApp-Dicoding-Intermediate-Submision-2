package com.example.storyapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.data.servicelocator.Injection
import com.example.storyapp.data.repo.StoryRepository
import com.example.storyapp.ui.authentication.login.LoginViewModel
import com.example.storyapp.ui.authentication.register.RegisterViewModel
import com.example.storyapp.ui.primary.add.AddStoryViewModel
import com.example.storyapp.ui.primary.detail.DetailViewModel
import com.example.storyapp.ui.primary.list.ListStoryViewModel
import com.example.storyapp.ui.primary.maps.StoryLocationViewModel

class PrimaryViewModelFactory private constructor(private val storyRepository: StoryRepository):
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(storyRepository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(storyRepository) as T
        }
        if (modelClass.isAssignableFrom(ListStoryViewModel::class.java)){
            return ListStoryViewModel(storyRepository) as T
        }
        if (modelClass.isAssignableFrom(AddStoryViewModel::class.java)){
            return AddStoryViewModel(storyRepository) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(storyRepository) as T
        }
        if (modelClass.isAssignableFrom(StoryLocationViewModel::class.java)){
            return StoryLocationViewModel(storyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: PrimaryViewModelFactory? = null
        fun getInstance(context: Context): PrimaryViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: PrimaryViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}