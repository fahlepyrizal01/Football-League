package com.rizalfahlepi.footballleague.activity.search

import android.text.format.DateUtils
import android.view.KeyEvent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.rizalfahlepi.footballleague.ElapsedTimeIdlingResource
import com.rizalfahlepi.footballleague.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class SearchMatchLeagueActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchMatchLeagueActivity::class.java)

    @Before
    fun resetTimeout() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout(26, TimeUnit.SECONDS)
    }

    @Test
    fun testAppBehaviour() {
        IdlingPolicies.setMasterPolicyTimeout((DateUtils.SECOND_IN_MILLIS * 3) * 2, TimeUnit.MILLISECONDS)
        IdlingPolicies.setIdlingResourceTimeout((DateUtils.SECOND_IN_MILLIS * 3) * 2, TimeUnit.MILLISECONDS)

        onView(withId(R.id.cardViewSearch)).perform(click())
        onView(withId(R.id.searchView)).perform(typeText("Madrid"))
        onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        val idlingResource1 = ElapsedTimeIdlingResource((DateUtils.SECOND_IN_MILLIS * 3))
        IdlingRegistry.getInstance().register(idlingResource1)
        onView(withId(R.id.recyclerViewMatch)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResource1)

        onView(withId(R.id.cardViewSearch)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(clearText())
        onView(withId(R.id.searchView)).perform(typeText("Lakers"))
        onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        val idlingResource2 = ElapsedTimeIdlingResource((DateUtils.SECOND_IN_MILLIS * 3))
        IdlingRegistry.getInstance().register(idlingResource2)
        onView(withId(R.id.textViewSearchNotFound)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResource2)

        onView(withId(R.id.textViewChooseTypeSearch)).perform(click())
        onView(withText(R.string.text_team)).perform(click())
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        val idlingResource3 = ElapsedTimeIdlingResource((DateUtils.SECOND_IN_MILLIS * 3))
        IdlingRegistry.getInstance().register(idlingResource3)
        onView(withId(R.id.textViewSearchNotFound)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResource3)

        onView(withId(R.id.cardViewSearch)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(clearText())
        onView(withId(R.id.searchView)).perform(typeText("Barcelona"))
        onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        val idlingResource4 = ElapsedTimeIdlingResource((DateUtils.SECOND_IN_MILLIS * 3))
        IdlingRegistry.getInstance().register(idlingResource4)
        onView(withId(R.id.recyclerViewMatch)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResource4)
    }

    @Test
    fun testRecyclerViewBehaviour() {
        IdlingPolicies.setMasterPolicyTimeout((DateUtils.SECOND_IN_MILLIS * 3) * 2, TimeUnit.MILLISECONDS)
        IdlingPolicies.setIdlingResourceTimeout((DateUtils.SECOND_IN_MILLIS * 3) * 2, TimeUnit.MILLISECONDS)

        onView(withId(R.id.cardViewSearch)).perform(click())
        onView(withId(R.id.searchView)).perform(typeText("City"))
        onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        val idlingResource1 = ElapsedTimeIdlingResource((DateUtils.SECOND_IN_MILLIS * 3))
        IdlingRegistry.getInstance().register(idlingResource1)
        onView(withId(R.id.recyclerViewMatch)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerViewMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.recyclerViewMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }
}