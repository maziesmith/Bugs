package id.ac.ui.cs.mobileprogramming.bugs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private final boolean DEBUG_MODE = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread loading = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    finishLoading();
                } finally {
                    finishLoading();
                }
            }
        };

        loading.start();
    }

    private void finishLoading() {
        if (DEBUG_MODE) {
            startActivity(new Intent(this, TestScreen.class));
        } else {
            startActivity(new Intent(this, MainScreen.class));
        }
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
