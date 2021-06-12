package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean bartIsShowing = true;

    public void fadeBart(View view){

        Log.i("info","Image View pressed");
        ImageView bartImageView = (ImageView)findViewById(R.id.bartImageView);
        ImageView homerImageView = (ImageView)findViewById(R.id.homerImageView);
//        if(bartIsShowing) {
//            bartIsShowing = false;
//            bartImageView.animate().alpha(0).setDuration(2000);
//            homerImageView.animate().alpha(1).setDuration(2000);
//        }else{
//            bartIsShowing = true;
//            bartImageView.animate().alpha(1).setDuration(2000);
//            homerImageView.animate().alpha(0).setDuration(2000);
//        }
        bartImageView.animate().rotationBy(180).setDuration(500);
    }

    public void fadeHomer(View view){

        Log.i("info","Image View (homer) pressed");
        ImageView bartImageView = (ImageView)findViewById(R.id.bartImageView);
        ImageView homerImageView = (ImageView)findViewById(R.id.homerImageView);
        homerImageView.animate().alpha(0).setDuration(2000);
        bartImageView.animate().alpha(1).setDuration(2000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bartImageView = (ImageView)findViewById(R.id.bartImageView);
        bartImageView.setX(-2000);
        bartImageView.animate().translationXBy(2000).rotation(3600).setDuration(2000);
    }
}