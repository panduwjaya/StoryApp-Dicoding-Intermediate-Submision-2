package com.example.storyapp.data.servicelocator

import android.content.Context
import com.example.storyapp.data.network.ApiConfig
import com.example.storyapp.data.repo.StoryRepository
import com.example.storyapp.data.token.TokenPreference
import com.example.storyapp.ui.primary.dark.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val pref = TokenPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.readToken().first() }
        val apiConfig = ApiConfig.getApiService(user)
        return StoryRepository.getInstance(apiConfig, pref)
    }
}