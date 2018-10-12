package id.ac.ui.cs.mobileprogramming.bugs;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.ui.cs.mobileprogramming.bugs.service.DownloadService;
import id.ac.ui.cs.mobileprogramming.bugs.service.WorkerResultReceiver;

public class TestScreen extends AppCompatActivity implements WorkerResultReceiver.Receiver {
    @BindView(R2.id.test_data) TextView result;

    private WorkerResultReceiver resultReceiver;

    @OnClick(R2.id.test_button) void run() {
        DownloadService.enqueueWork(this, resultReceiver);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        resultReceiver = new WorkerResultReceiver(new Handler());
        resultReceiver.setReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DownloadService.DOWNLOAD_RESULT_CODE:
                if (resultData != null) {
                    showData(resultData.getString("data"));
                }
                break;
        }
    }

    private void showData(String data) {
        result.setText(data);
    }
}
