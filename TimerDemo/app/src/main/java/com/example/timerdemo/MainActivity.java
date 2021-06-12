package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long l) {
                Log.i("seconds left",String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                Log.i("We're done!","No more countdown left");
            }
        }.start();
    }
}