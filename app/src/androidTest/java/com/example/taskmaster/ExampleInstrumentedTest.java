package com.example.taskmaster;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    // test when change setting name it displayed on the home page
    @Test
    public void useAppContext() {
        // 1- press setting btn at home
        onView(withId(R.id.homeSettingsBtn)).perform(click());
        // 2- fill the field on setting page
        onView(withId(R.id.settingsTextField))
                .perform(typeText("osama"), closeSoftKeyboard());
        // 3- click save btn on setting page
        onView(withId(R.id.settingsSaveBtn)).perform(click());
        // 4- press back
        Espresso.pressBackUnconditionally();
        // 5- see the result on home page
        onView(withId(R.id.homeUserTasksTitle)).check(matches(withText("osama tasks")));
    }

    // test if button on setting page is displayed
    @Test
    public void useAppContext2() {
        // 1- press the setting btn on home page
        onView(withId(R.id.homeSettingsBtn)).perform(click());
        // 2- check if the setting text field is displayed
        onView(withId(R.id.settingsTextField)).check(matches(isDisplayed()));
    }

    // test home page recycler view is displayed
    @Test
    public void useAppContext3() {
        // check if the recycler view is displayed
        onView(withId(R.id.taskListRecyclerView)).check(matches(isDisplayed()));
    }

    // test when press on 1st task on homepage it will show correct task on details page
    @Test
    public void useAppContext4() {
        // 1- press 1st task on homepage
        onView(withId(R.id.taskListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // 2- check details page
        onView(withId(R.id.taskDetailTitle)).check(matches(withText("play football complete")));
    }

    // test when press on 2nd task on homepage it will show correct task on details page
    @Test
    public void useAppContext5() {
        // 1- press 2nd task on homepage
        onView(withId(R.id.taskListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // 2- check details page
        onView(withId(R.id.taskDetailTitle)).check(matches(withText("buy computer in progress")));
    }
}