package com.example.android.scorekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.scorekeeper.MainActivity.PLAYER_1;
import static com.example.android.scorekeeper.MainActivity.PLAYER_2;

public class ScoreKeeper extends AppCompatActivity {

    static final String STATE_SCORE_A = "saveTeamAscore";
    static final String STATE_SCORE_B = "saveTeamBscore";

    // Tracks the score for Team A
    int scoreTeamA;
    // Tracks the score for Team B
    int scoreTeamB;

    private String gamer1;
    private String gamer2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper);

        displayForTeamA(0);
        displayForTeamB(0);


        // Save the user's current game state
        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt(STATE_SCORE_A, scoreTeamA);
            scoreTeamB = savedInstanceState.getInt(STATE_SCORE_B, scoreTeamB);
            displayForTeamA(scoreTeamA);
            displayForTeamB(scoreTeamB);
        }


        // Get the message from the intent
        Intent intent = getIntent();


        gamer1 = intent.getStringExtra(PLAYER_1);
        gamer2 = intent.getStringExtra(PLAYER_2);

        TextView nameA;
        TextView nameB;

        nameA = (TextView) findViewById(R.id.teamNameA);
        nameB = (TextView) findViewById(R.id.teamNameB);
        nameA.setText(gamer1);
        nameB.setText(gamer2);


        // Welcome message
        Toast.makeText(this, "Prepare for the battle: " + "\n" + gamer1 + " vs " + gamer2,
                Toast.LENGTH_LONG).show();
    }


    /**
     * Display the given score for the Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);

        if (scoreTeamA <= -1) {
            Toast.makeText(this, "That sucks, " + gamer1, Toast.LENGTH_SHORT).show();
        }



        if (scoreTeamA >= 20) {
            Toast.makeText(this, "Legendary " + gamer1, Toast.LENGTH_SHORT).show();
        }

        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase the score for Team A by 1 points.
     */
    public void submitOnePointForTeamA(View view) {
        scoreTeamA++;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void submitThreePointsForTeamA(View view) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);

        // If the button +3 is pushed two times in a row
//        if (scoreTeamB ) {
//            Toast.makeText(this, "You're doing great!", Toast.LENGTH_SHORT).show();
//       }
    }

    /**
     * Decrease the score for Team A by 1 points.
     */
    public void submitMinusOnePointForTeamA(View view) {
        scoreTeamA--;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Display the given score for the Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);

        if (scoreTeamB <= -1) {
            Toast.makeText(this, "That sucks " + gamer2, Toast.LENGTH_SHORT).show();
        }

        if (scoreTeamB >= 20) {
            Toast.makeText(this, "Legendary " + gamer2, Toast.LENGTH_SHORT).show();
        }

        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    public void submitOnePointForTeamB(View view) {
        scoreTeamB++;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void submitThreePointsForTeamB(View view) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Decrease the score for Team A by 1 point.
     */
    public void submitMinusOnePointForTeamB(View view) {
        scoreTeamB--;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Reset Button.
     * Display default text for the winners text
     * Display Toast message
     */
    public void reset(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);

        // Reset winners text
        // Not working by using resources R.string.winners_text
        TextView winnerText = (TextView) findViewById(R.id.winnerText);
        winnerText.setText("Who\'s the best?");

        // Toast message for a new game
        Toast.makeText(this, "Here we go again " + gamer1 + " and " + gamer2,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Finish Button.
     * Display three possible ways of ending the game
     */
    public void finish(View view) {

        String winningPlayer;

        if (scoreTeamA > scoreTeamB) {
            winningPlayer = gamer1;
            // Winner text
            TextView winnerText = (TextView) findViewById(R.id.winnerText);
            winnerText.setText(winningPlayer + " wins!");
        } else if (scoreTeamA < scoreTeamB) {
            winningPlayer = gamer2;
            // Winner text
            TextView winnerText = (TextView) findViewById(R.id.winnerText);
            winnerText.setText(winningPlayer + " wins!");
        } else {
            TextView winnerText = (TextView) findViewById(R.id.winnerText);
            winnerText.setText(" ");
            Toast.makeText(this, "The score between " + gamer1 + " and " + gamer2 + " is equal!",
                    Toast.LENGTH_LONG).show();
        }

        /** TODO
         * Show a pop up Winning image
         * Set image to be visible
         */
//        ImageView winnerImage = (ImageView) findViewById(R.id.winnerImage);
//        winnerImage.setImageResource(R.drawable.avatar_4);

    }


    // Save state when rotating
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(STATE_SCORE_A, scoreTeamA);
        savedInstanceState.putInt(STATE_SCORE_B, scoreTeamB);

        super.onSaveInstanceState(savedInstanceState);

    }

    // Restore state when rotating
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getInt(STATE_SCORE_A);
        savedInstanceState.getInt(STATE_SCORE_B);
    }
}
