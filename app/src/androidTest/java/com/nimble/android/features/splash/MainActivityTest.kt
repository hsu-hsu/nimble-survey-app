package com.nimble.android.features.splash


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.nimble.android.MainActivity
import com.nimble.android.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginInputDataTest() {

        onView(withId(R.id.email_edit))
            .check(matches(isDisplayed()))

        onView(withId(R.id.email_edit))
            .perform(ViewActions.typeText("hsuyethin@gmail.com"))
            .check(matches(withText("hsuyethin@gmail.com")))

        onView(withId(R.id.password_edit))
            .perform(ViewActions.typeText("hsuyeethin"))
            .check(matches(withText("hsuyeethin")))

        onView(withId(R.id.login_button))
            .check(matches(isDisplayed()))
    }
}
