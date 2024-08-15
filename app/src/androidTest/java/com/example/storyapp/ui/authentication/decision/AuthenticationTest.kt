package com.example.storyapp.ui.authentication.decision

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.storyapp.R
import com.example.storyapp.ui.authentication.auth.AuthenticationActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AuthenticationTest{
    private val dummyName = "tester"
    private val dummyEmail = "testing89@gmail.com"
    private val dummyPassword = "123456789"

    @Before
    fun setup(){
        ActivityScenario.launch(AuthenticationActivity::class.java)
    }

    @Test
    fun decisionTest(){
        onView(withId(R.id.btn_signup_fragment))
    }

    @Test
    fun registerTest(){
        onView(withId(R.id.ed_register_name)).perform(typeText(dummyName), closeSoftKeyboard())
        onView(withId(R.id.ed_register_email)).perform(typeText(dummyEmail), closeSoftKeyboard())
        onView(withId(R.id.ed_register_password)).perform(typeText(dummyPassword), closeSoftKeyboard())

        onView(withId(R.id.btn_signup))
    }

    @Test
    fun loginTest(){
        onView(withId(R.id.ed_login_email)).perform(typeText(dummyEmail), closeSoftKeyboard())
        onView(withId(R.id.ed_login_password)).perform(typeText(dummyPassword), closeSoftKeyboard())

        onView(withId(R.id.btn_login))
    }
}