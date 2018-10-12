package id.ac.ui.cs.mobileprogramming.bugs.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class SampleService extends IntentService {

    public SampleService() {
        super("Sample Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String dataString = intent.getDataString();

    }
}
