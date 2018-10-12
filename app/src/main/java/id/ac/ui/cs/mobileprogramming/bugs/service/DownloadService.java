package id.ac.ui.cs.mobileprogramming.bugs.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

public class DownloadService extends JobIntentService {
    public static final int JOB_ID = 3001;
    public static final String TAG = "DownloadService";
    public static final String RECEIVER = "RECEIVER";
    public static final int DOWNLOAD_RESULT_CODE = 3000;
    public static final String ACTION_DOWNLOAD = "DOWNLOAD";

    private ResultReceiver resultReceiver;

    public DownloadService() {
        super();
    }

    public static void enqueueWork(Context context, WorkerResultReceiver resultReceiver) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(RECEIVER, resultReceiver);
        intent.setAction(ACTION_DOWNLOAD);
        enqueueWork(context, DownloadService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
//        Log.d(TAG, "handle intent [" + intent + "]");
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_DOWNLOAD:
                    resultReceiver = intent.getParcelableExtra(RECEIVER);
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                sleep(3000);
                            } catch (InterruptedException e) {
                                sentRequest();
                            } finally {
                                sentRequest();
                            }
                        }
                    };
                    thread.start();
            }
        }
    }

    private void sentRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("data", "");
        resultReceiver.send(DOWNLOAD_RESULT_CODE, bundle);
    }
}
