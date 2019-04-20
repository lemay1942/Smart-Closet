package com.example.smartcloset;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    ProgressBar mProgBar;
    Handler handler = new Handler();
    int value = 0;
    int add = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgBar = findViewById(R.id.progressBar2);
        startLoading();
    }

    private void startLoading() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    value = value + add;
                    if (mProgBar.getProgress() == mProgBar.getMax()) {
                        finish();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgBar.setProgress(value);
                        }
                    });
                    try {
                        Thread.sleep(35);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        t.start();
    }
}
