package com.github.ayltai.newspaper;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.ayltai.newspaper.util.SuppressFBWarnings;

import junit.framework.AssertionFailedError;

@RunWith(AndroidJUnit4.class)
@LargeTest
public final class MainActivityTest {
    @SuppressFBWarnings("URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @SuppressWarnings("deprecation")
    @Before
    public void setUp() {
        final Activity activity = this.activityRule.getActivity();

        // Makes sure the device is unlocked
        activity.runOnUiThread(() -> activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON));
    }

    @Test
    public void tapThroughAllTabs() {
        for (final String category : InstrumentationRegistry.getTargetContext().getResources().getStringArray(R.array.pref_category_entries)) {
            Espresso.onView(ViewMatchers.withId(R.id.navigate_next))
                .perform(ViewActions.click());

            Espresso.onView(ViewMatchers.withId(R.id.collapsingToolbarLayout))
                .check((view, noViewFoundException) -> {
                    if (noViewFoundException == null) {
                        final CollapsingToolbarLayout toolbar = (CollapsingToolbarLayout)view;

                        if (toolbar.getTitle() == null) throw new AssertionFailedError("Expected: " + category + "\n     Got: null");
                        if (category.equals(toolbar.getTitle().toString())) throw new AssertionFailedError("Expected: " + category + "\n     Got: " + toolbar.getTitle());
                    }
                });
        }
    }

    @Test
    public void openFirstItem() {
        // Switches to "HK" tab
        Espresso.onView(ViewMatchers.withId(R.id.navigate_next))
            .perform(ViewActions.click());

        // Opens first item
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Asserts that the title is expected
        Espresso.onView(ViewMatchers.withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.withText("馬時亨冀3月就票價機制達協議 不覺得有驚天動地改變")));
    }
}
