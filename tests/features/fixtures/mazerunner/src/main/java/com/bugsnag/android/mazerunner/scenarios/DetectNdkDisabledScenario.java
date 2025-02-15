package com.bugsnag.android.mazerunner.scenarios;

import android.content.Context;
import com.bugsnag.android.Configuration;
import android.support.annotation.NonNull;

public class DetectNdkDisabledScenario extends Scenario {

    static {
        System.loadLibrary("bugsnag-ndk");
        System.loadLibrary("monochrome");
        System.loadLibrary("entrypoint");
    }

    public native void crash();

    public DetectNdkDisabledScenario(@NonNull Configuration config, @NonNull Context context) {
        super(config, context);
        config.setDetectNdkCrashes(false);
    }

    @Override
    public void run() {
        super.run();
        crash();
    }
}
