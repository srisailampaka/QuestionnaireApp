package com.questionnaire

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.questionnaire.ui.MainActivity
import com.questionnaire.ui.adapter.ViewpagerAdapter
import kotlinx.android.synthetic.main.questionnaire_fragment.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    @Rule
    @JvmField
    public var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
            MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.questionnaire", appContext.packageName)
    }


    @Test
    fun performQuestionnaire() {
        val questionsList = (mActivityRule.activity.viewPager.adapter as ViewpagerAdapter).questionsList
        for (i in 0 until questionsList.size) {
            Espresso.onView(ViewMatchers.withText(questionsList[i].options[0].answer)).perform(click())
            Espresso.onView(ViewMatchers.withId(R.id.viewPager)).perform(swipeLeft());
        }
    }
}
