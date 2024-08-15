package com.example.storyapp.ui.primary.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.storyapp.R
import com.example.storyapp.ui.authentication.auth.AuthenticationActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainTest{

    @Before
    fun setup(){
        ActivityScenario.launch(AuthenticationActivity::class.java)
    }

    @Test
    fun openSettingTest(){
        onView(ViewMatchers.withId(R.id.settings_menu))
    }

    @Test
    fun openMapsTest(){
        onView(ViewMatchers.withId(R.id.fab_maps))
    }

    @Test
    fun logOutTest(){
        onView(ViewMatchers.withId(R.id.action_logout))
    }
}