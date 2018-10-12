package id.ac.ui.cs.mobileprogramming.bugs.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class WorkerResultReceiver extends ResultReceiver {
    public static final String TAG = "ResultReceiver";
    private Receiver receiver;

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    public WorkerResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (receiver != null) {
//            Log.d(TAG, "worker receive with result code [" + resultCode + "]");
            receiver.onReceiveResult(resultCode, resultData);
        }
    }
}
