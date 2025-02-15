package com.bugsnag.android.mazerunner.scenarios;

import android.content.Context;

import com.bugsnag.android.Configuration;

import android.support.annotation.NonNull;

public class CXXThrowSomethingScenario extends Scenario {

    static {
        System.loadLibrary("bugsnag-ndk");
        System.loadLibrary("monochrome");
        System.loadLibrary("entrypoint");
    }

    public native void crash(int num);

    public CXXThrowSomethingScenario(@NonNull Configuration config, @NonNull Context context) {
        super(config, context);
    }

    @Override
    public void run() {
        super.run();
        crash(23);
    }
}
