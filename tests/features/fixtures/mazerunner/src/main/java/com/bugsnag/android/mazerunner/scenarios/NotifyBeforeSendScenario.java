package com.bugsnag.android.mazerunner.scenarios;

import com.bugsnag.android.Bugsnag;
import com.bugsnag.android.BeforeSend;
import com.bugsnag.android.Configuration;
import com.bugsnag.android.Report;
import com.bugsnag.android.Severity;

import android.content.Context;
import android.support.annotation.NonNull;


public class NotifyBeforeSendScenario extends Scenario {

    public NotifyBeforeSendScenario(@NonNull Configuration config, @NonNull Context context) {
        super(config, context);
        config.beforeSend(new BeforeSend() {
            @Override
            public boolean run(Report report) {
                report.getError().setContext("RESET");
                report.getError().setSeverity(Severity.ERROR);

                return true;
            }
        });
    }

    @Override
    public void run() {
        super.run();
        Bugsnag.notify(new Exception("Registration failure"));
    }
}
