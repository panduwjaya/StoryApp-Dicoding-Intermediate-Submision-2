package com.example.storyapp.ui.primary.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.R
import com.example.storyapp.ui.primary.dark.DarkViewModel
import com.example.storyapp.ui.primary.dark.DarkViewModelFactory
import com.example.storyapp.ui.primary.dark.SettingPreferences
import com.example.storyapp.ui.primary.dark.dataStore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val darkViewModel = ViewModelProvider(this, DarkViewModelFactory(pref)).get(
            DarkViewModel::class.java
        )

        darkViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}