package com.dmp.handlerthread;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private HandlerThread handlerThread = new HandlerThread("HandlerThread");
    private Handler threadHandler;


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.resultTxt);
        handlerThread.start();
        threadHandler = new Handler(handlerThread.getLooper());
    }

    public void onStartThread(View view) {
        threadHandler.post(new ExpThread());
    }

    public void onStopThread(View view) {

    }

    static class ExpThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep( 1000);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }
}