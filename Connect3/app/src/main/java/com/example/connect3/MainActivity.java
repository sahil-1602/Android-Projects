package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {




    // 0: yellow, 1: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Somone has won!

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Yellow";

                    } else {

                        winner = "Red";

                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }

            }
        }
    }

    public void playAgain(View view) {

        Log.i("Info","Play again clicked");
//
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
//
        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

    }


//    // 0:Yellow 1:Red 2:Empty
//
//    int currentPlayer = 0;
//    int[] gameState = {2,2,2,2,2,2,2,2,2};
//    int[][] winningState ={{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 4, 8},{2, 4, 6},{0, 3, 6},{1, 4, 7},{2, 5, 8}};
//    boolean gameActive = true;
//
//    public void dropIn(View view){
//        ImageView counter = (ImageView) view;
//
//
//        int clickedTag = Integer.parseInt(counter.getTag().toString());
//        if(gameState[clickedTag]==2 && gameActive) {
//
//            counter.setTranslationY(-1500);
//            gameState[clickedTag]=currentPlayer;
//            if (currentPlayer == 0) {
//                currentPlayer = 1;
//                counter.setImageResource(R.drawable.yellow);
//            } else {
//                currentPlayer = 0;
//                counter.setImageResource(R.drawable.red);
//            }
//            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
//
//            for (int[] winningPosition : winningState) {
//                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
//                    // someone has won
//                    gameActive=false;
//                    String winner = "";
//                    if (currentPlayer == 1) {
//                        winner = "Yellow";
//                    } else {
//                        winner = "Red";
//                    }
//                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
//
//                    Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
//
//                    winnerTextView.setText(winner+" has won!");
//
//                    winnerTextView.setVisibility(View.VISIBLE);
//                    playAgainButton.setVisibility(View.VISIBLE);
//
//                }
//            }
//        }
//    }
//
//    public void playAgain(View view){
//        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
//        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
//        winnerTextView.setVisibility(View.INVISIBLE);
//        playAgainButton.setVisibility(View.INVISIBLE);
//
//        GridLayout gridLayout =(GridLayout)findViewById(R.id.gridLayout);
//        for(int i=0; i<gridLayout.getChildCount();i++){
//            ImageView counter = (ImageView) gridLayout.getChildAt(i);
//            counter.setImageDrawable(null);
//        }
//
//        for(int i=0; i<gameState.length; i++){
//            gameState[i]=2;
//        }
//        gameActive=true;
//        currentPlayer=0;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}