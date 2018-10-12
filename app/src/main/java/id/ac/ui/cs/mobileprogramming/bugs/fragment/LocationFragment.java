package id.ac.ui.cs.mobileprogramming.bugs.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.ui.cs.mobileprogramming.bugs.R;
import id.ac.ui.cs.mobileprogramming.bugs.service.DownloadService;
import id.ac.ui.cs.mobileprogramming.bugs.service.WorkerResultReceiver;

public class LocationFragment extends Fragment implements WorkerResultReceiver.Receiver {
    @BindView(R.id.bug) ImageView bug;
    @BindView(R.id.button_click) Button buttonClick;
    @BindString(R.string.who_said_click) String whoSaidClick;
    @BindString(R.string.click_me) String clickMe;
    @BindString(R.string.ok) String gotIt;
    @BindString(R.string.what_happened) String whatHappened;
    @BindString(R.string.glitches) String glitches;

    private WorkerResultReceiver resultReceiver;
    private AlertDialog.Builder builder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, root);

        resultReceiver = new WorkerResultReceiver(new Handler());
        resultReceiver.setReceiver(this);

        builder = new AlertDialog.Builder(getContext()).setMessage(whoSaidClick);
        builder.setPositiveButton(gotIt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.create().show();
            }
        });

        return root;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DownloadService.DOWNLOAD_RESULT_CODE:
                if (resultData != null) {
                    glitch();
                }
                break;
        }
    }

    @OnClick(R.id.button_click) void sentRequest() {
        Toast.makeText(getContext(), whatHappened, Toast.LENGTH_SHORT).show();
        DownloadService.enqueueWork(getContext(), resultReceiver);
    }

    private void glitch() {
        Toast.makeText(getContext(), glitches, Toast.LENGTH_SHORT).show();
        bug.setImageAlpha(0);
        builder.create().show();
    }
}
