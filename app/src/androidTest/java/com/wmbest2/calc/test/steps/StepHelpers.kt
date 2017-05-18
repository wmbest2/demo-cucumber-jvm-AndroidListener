package com.wmbest2.calc.test.steps

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

import cucumber.api.java.en.Given

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.InstrumentationRegistry.getTargetContext

fun launchDefaultActivity(): Activity {
    val launchIntent = getTargetContext().defaultPackageIntent
    return launchActivityWithIntent(launchIntent)
}

private val Context.defaultPackageIntent: Intent
    get() = packageManager.getLaunchIntentForPackage(packageName)

private fun launchActivityWithIntent(intent: Intent): Activity {
    val activity = getInstrumentation().startActivitySync(intent)
    getInstrumentation().waitForIdleSync()
    return activity
}
