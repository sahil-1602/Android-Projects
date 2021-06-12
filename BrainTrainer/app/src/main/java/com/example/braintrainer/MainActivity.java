package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton goImageButton ;
    TextView goTextView;
    ImageButton ansImageButton1;
    ImageButton ansImageButton2;
    ImageButton ansImageButton3;
    ImageButton ansImageButton4;
    TextView scoreTextView;
    TextView timerTextView;
    TextView ansTextView1;
    TextView ansTextView2;
    TextView ansTextView3;
    TextView ansTextView4;
    TextView sumTextView;
    TextView judgeTextView;
    Button playAgainButton;
    int locationOfCorrectAnswer;
    int score=0;
    int noOfQuestions =0;

    ArrayList<Integer> answers = new ArrayList<>();


    public  void chooseAnswer(View view){
        judgeTextView.setVisibility(View.VISIBLE);
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            Log.i("Correct Ans","You got it!");
            judgeTextView.setText("CORRECT :)");
            score++;

        }else{
            Log.i("Wrong answer",":(");
            judgeTextView.setText("WRONG :(");
        }
        noOfQuestions++;

        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));

        getQuestions();
    }


    public void clickGo(View view){
        goImageButton.setVisibility(View.INVISIBLE);
        goTextView.setText("");
        ansImageButton1.setVisibility(View.VISIBLE);
        ansImageButton2.setVisibility(View.VISIBLE);
        ansImageButton3.setVisibility(View.VISIBLE);
        ansImageButton4.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        ansTextView1.setVisibility(View.VISIBLE);
        ansTextView2.setVisibility(View.VISIBLE);
        ansTextView3.setVisibility(View.VISIBLE);
        ansTextView4.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        getTimer();

    }

    public  void getTimer(){
        CountDownTimer countDownTimer = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                judgeTextView.setText("TIME'S UP!!");
                playAgainButton.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mediaPlayer.start();
                ansImageButton1.setEnabled(false);
                ansImageButton2.setEnabled(false);
                ansImageButton3.setEnabled(false);
                ansImageButton4.setEnabled(false);

            }
        }.start();
    }

    public  void getQuestions(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for(int i=0 ; i<4 ; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        ansTextView1.setText(Integer.toString(answers.get(0)));
        ansTextView2.setText(Integer.toString(answers.get(1)));
        ansTextView3.setText(Integer.toString(answers.get(2)));
        ansTextView4.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goImageButton = findViewById(R.id.goImageButton);
        goTextView = findViewById(R.id.goTextView);
        ansImageButton1=findViewById(R.id.ansImageButton1);
        ansImageButton2=findViewById(R.id.ansImageButton2);
        ansImageButton3=findViewById(R.id.ansImageButton3);
        ansImageButton4=findViewById(R.id.ansImageButton4);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        ansTextView1 = findViewById(R.id.ansTextView1);
        ansTextView2 = findViewById(R.id.ansTextView2);
        ansTextView3 = findViewById(R.id.ansTextView3);
        ansTextView4 = findViewById(R.id.ansTextView4);
        sumTextView = findViewById(R.id.sumTextView);
        judgeTextView = findViewById(R.id.judgeTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        getQuestions();

    }

    public void playAgain(View view){
        score=0;
        noOfQuestions=0;
        scoreTextView.setText("0/0");
        judgeTextView.setText("");
        ansImageButton1.setEnabled(true);
        ansImageButton2.setEnabled(true);
        ansImageButton3.setEnabled(true);
        ansImageButton4.setEnabled(true);
        getQuestions();
        clickGo(judgeTextView);

    }
}