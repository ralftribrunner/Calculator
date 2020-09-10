package com.example.calculator



import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    fun getActivity() = activityRule.activity
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.calculator", appContext.packageName)
    }

    @Test //UI test
    fun Buttontest() {
        onView(withId(R.id._2)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        onView(withId(R.id.tv_first)).check(matches(withText("")))
        onView(withId(R.id.tv_second)).check(matches(withText("")))
        onView(withId(R.id.textView2)).check(matches(withText("")))
        onView(withId(R.id._1)).check(matches(isDisplayed()))
        onView(withId(R.id._2)).perform(click())
        onView(withId(R.id.tv_first)).check(matches(withText("2")))
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.textView2)).check(matches(withText("+")))
        onView(withId(R.id.tv_second)).perform(typeText("6"))
        onView(withId(R.id.tv_second)).check(matches(withText("6")))
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.answer)).perform(click())
        onView(withId(R.id.textView2)).check(matches(withText("ANS")))
        onView(withId(R.id.tv_first)).check(matches(withText("8.0")))

    }

    @Test //Instrumented Test for calculation
    fun CalculationTest() {
        getActivity().setTexts("15","12","-")
        getActivity().calculating()
        getActivity().updateView()
        assertEquals(getActivity().getFirst(),"3.0")
        assertEquals(getActivity().getSecond(),"")
        assertEquals(getActivity().getOp(),Operators.ANS)

    }

    @Test //Instrumented Test for checking Instance saving
    fun saveInstanceTest() {

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActivity().setTexts("30","45","×")
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        assertEquals(getActivity().getFirst(),"30")
        assertEquals(getActivity().getSecond(),"45")
        assertEquals(getActivity().getOp(),Operators.MULTI)
    }



}
