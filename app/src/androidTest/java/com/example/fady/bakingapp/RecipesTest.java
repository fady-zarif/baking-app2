package com.example.fady.bakingapp;

import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.schibsted.spain.barista.BaristaSleepActions;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Fady on 2017-06-14.
 */

@RunWith(AndroidJUnit4.class)
public class RecipesTest {
    // Convenience helper

    @Rule
    public ActivityTestRule<RecipesActivity> activityTestRule = new ActivityTestRule<RecipesActivity>(RecipesActivity.class);

    public static int withRecyclerView(final int recyclerViewId) {
        return (recyclerViewId);
    }

    @Test
    public void onRecyclerItemClick() {
        BaristaSleepActions.sleep(3000);
        onView(withId(R.id.RecipesRecyclerView)).perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void checkText() {
        BaristaSleepActions.sleep(3000);
        onView(ViewMatchers.withId(R.id.RecipesRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withText("Brownies")).check(matches(isDisplayed()));
    }

    @Test
    public void Ingredient() {
        BaristaSleepActions.sleep(3000);
        onView(ViewMatchers.withId(R.id.RecipesRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(ViewMatchers.withId(R.id.details_Recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withText("unsalted butter")).check(matches(isDisplayed()));
    }


    @Test
    public void stepDetails() {
        BaristaSleepActions.sleep(3000);
        onView(ViewMatchers.withId(R.id.RecipesRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(ViewMatchers.withId(R.id.details_Recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        BaristaSleepActions.sleep(3000);
        onView(withText("Combine dry ingredients.")).check(matches(isDisplayed()));
        onView(withId(R.id.nextStep)).check(matches(isEnabled()));
    }

}
