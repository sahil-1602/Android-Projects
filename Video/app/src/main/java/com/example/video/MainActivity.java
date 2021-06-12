package com.example.video;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        videoView.setVideoPath("android.resources://" + getPackageName() +"/"+ R.raw.demo);
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        videoView.start();

//        String uriPath = "android.resources://" + getPackageName() +"/"+ R.raw.introduction;
//        Uri uri = Uri.parse(uriPath);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();

//
//        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.crimson);
//        mediaPlayer.start();
        mediaPlayer = MediaPlayer.create(this,R.raw.crimson);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volume = findViewById(R.id.volumeSeekBar);
        volume.setMax(maxVolume);
        volume.setProgress(currVolume);
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("SeekBar Moved",Integer.toString(i));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar scrubbingControl = findViewById(R.id.scrubSeekBar);
        scrubbingControl.setMax(mediaPlayer.getDuration());

        scrubbingControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Scrubber changed",Integer.toString(i));
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubbingControl.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,10000);
    }

    public void play(View view){
        Log.i("info","clicked play");

        mediaPlayer.start();
    }


    public void pause(View view){
        Log.i("info","clicked pause");

        mediaPlayer.pause();
    }
}