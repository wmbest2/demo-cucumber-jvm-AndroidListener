package com.wmbest2.calc.test.runner;

import android.os.Bundle;
import android.support.test.runner.*;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;

@CucumberOptions(
        features = "features",
        glue = "com.wmbest2.calc.test.steps"
)
public class CucumberTestRunner extends MonitoringInstrumentation {

    protected CucumberInstrumentationCore instrumentationCore =
            new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        instrumentationCore.create(bundle);
        start();
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}

