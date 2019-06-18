package com.ssuji.a3dotgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 : red, 1 : yellow, 2 : empty;
    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean gameActive = true;

    String winner = "";

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int gameCount = 0;

    public void dropIn(View view) {

        ImageView touch = (ImageView) view;
        int tagKeeper = Integer.parseInt(touch.getTag().toString());
        if (gameState[tagKeeper] == 2 && gameActive) {

            gameCount++;

            if (activePlayer==0) {

                touch.setImageResource(R.drawable.red);
                activePlayer = 1;
            }
            else {

                touch.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            touch.setTranslationY(-1500);
            touch.animate().translationYBy(1500).rotationBy(3600).setDuration(300);
            gameState[tagKeeper] = activePlayer;

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;

                    if (activePlayer == 0)
                        winner = "Yellow";
                    else
                        winner = "Red";

                    TextView gameOver = (TextView)findViewById(R.id.textView);
                    Button playAgain = (Button)findViewById(R.id.button);
                    gameOver.setText(winner + " has won!");
                    gameOver.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
                else if (gameCount == 9) {

                    Button playAgain = (Button)findViewById(R.id.button);
                    playAgain.setVisibility(View.VISIBLE);
                }

            }

        }
        else if (!gameActive){
            Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "A dot is already present", Toast.LENGTH_SHORT).show();
    }

    public void playAgain(View view) {

        TextView gameOver = findViewById(R.id.textView);
        Button playAgain = findViewById(R.id.button);
        gameOver.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        gameCount = 0;
        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView child = (ImageView)gridLayout.getChildAt(i);
            child.setImageDrawable(null);

        }
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
