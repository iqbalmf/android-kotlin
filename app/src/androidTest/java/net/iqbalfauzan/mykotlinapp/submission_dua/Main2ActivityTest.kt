package net.iqbalfauzan.mykotlinapp.submission_dua

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.R.id.*
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class Main2ActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(Main2Activity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        onView(withId(prev_match)).check(matches(isDisplayed()))
        onView(withId(next_match)).check(matches(isDisplayed())).perform(click())
        onView(withId(prev_match)).check(matches(isDisplayed())).perform(click())
        sleep(5000)
        onView(withId(list_prev)).check(matches(isDisplayed()))
        onView(withId(list_prev)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.list_prev)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12, click()))
        sleep(5000)
        //onView(withId(favorite_match)).check(matches(isDisplayed())).perform(click())
        /*onView(withId(R.id.list_fav)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))*/

        /*onView(withId(list_prev)).check(matches(isDisplayed()))
        onView(withId(list_prev)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.list_prev)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))*/
    }
    @Test
    fun testAppBehaviour() {
        onView(withId(prev_match)).check(matches(isDisplayed()))
        sleep(3000)
        onView(withId(list_prev)).check(matches(isDisplayed()))
        onView(withId(R.id.list_prev)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        sleep(3000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed())).perform(click())
        pressBack()

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(next_match)).perform(click())
        sleep(3000)
        onView(withId(list_next)).check(matches(isDisplayed()))
        onView(withId(R.id.list_next)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        sleep(3000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed())).perform(click())
        pressBack()

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorite_match)).perform(click())
        sleep(3000)
    }
}