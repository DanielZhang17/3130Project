package com.project.csci3130.dalrs;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RequireCouseTest {
    @Rule
    public ActivityTestRule<RequiredCourse> mActivity=new ActivityTestRule<>(RequiredCourse.class);
    @Test
    public void requiredata() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.CS)).perform(click());
    }
    @Test
    public void requiredataA() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.AppliedCS)).perform(click());
    }
    @Test
    public void requiredatam() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.Math)).perform(click());
    }
}
