package com.wmbest2.calc.test.steps;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;

import com.wmbest2.calc.R;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.*;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;

public class Steps {

    @After
    public void finish() {
        try {
            Espresso.pressBack();
        } catch (Exception e) {}
    }

    @Given("^I launch the app$")
    public void iLaunchTheApp() throws Throwable {
        StepHelpersKt.launchDefaultActivity();
        getInstrumentation().waitForIdleSync();
    }

    @Then("^the app should not crash$")
    public void theAppShouldNotCrash() throws Throwable { }

    @When("^I add (\\d+) and (\\d+)$")
    public void iAddAnd(String arg0, String arg1) throws Throwable {
        onView(withText(arg0)).perform(click());
        onView(withText("+")).perform(click());
        onView(withText(arg1)).perform(click());
        onView(withText("Enter")).perform(click());
    }

    @Then("^I should see (\\d+.\\d+)$")
    public void iShouldSee(String result) throws Throwable {
        onView(withId(R.id.result)).check(matches(withText(result)));
    }
}
