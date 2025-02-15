package com.bugsnag.android.mazerunner.scenarios;

import com.bugsnag.android.Bugsnag;

import android.content.Context;

import com.bugsnag.android.Configuration;

import android.support.annotation.NonNull;

public class CXXNativeBreadcrumbJavaNotifyScenario extends Scenario {
    static {
        System.loadLibrary("bugsnag-ndk");
        System.loadLibrary("monochrome");
        System.loadLibrary("entrypoint");
    }

    public native void activate();

    public CXXNativeBreadcrumbJavaNotifyScenario(@NonNull Configuration config, @NonNull Context context) {
        super(config, context);
    }

    @Override
    public void run() {
        super.run();
        activate();
        Bugsnag.notify(new Exception("Did not like"));
    }
}
