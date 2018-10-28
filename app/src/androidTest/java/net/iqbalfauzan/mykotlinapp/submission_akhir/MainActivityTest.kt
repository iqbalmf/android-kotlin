package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.R.id.*
import net.iqbalfauzan.mykotlinapp.R.string.matches
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testFootBallApp() {
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.matches)).perform(click())
        sleep(3000)
        onView(withId(R.id.list_next)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        sleep(3000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed())).perform(click())
        pressBack()

        //swipe ke kiri
        onView(withId(R.id.viewpager)).check(matches(isDisplayed()))
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        sleep(3000)
        onView(withId(R.id.list_prev)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        sleep(3000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed())).perform(click())
        pressBack()
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.teams)).perform(click())
        sleep(3000)
        onView(withId(R.id.list_team)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        sleep(3000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed())).perform(click())
        pressBack()
        sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(click())
        sleep(3000)
        onView(withId(R.id.viewpager)).check(matches(isDisplayed()))
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        sleep(3000)

    }
}