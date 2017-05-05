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

    public static final String STATE_SCORE_A = "save_team_A_score";
    public static final String STATE_SCORE_B = "save_team_B_score";
    public static final String SAVE_NAME_A = "save_name_A";
    public static final String SAVE_NAME_B = "save_name_b";


    int scoreTeamA; // Tracks the score for Team A
    int scoreTeamB; // Tracks the score for Team B

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

        TextView nameA = (TextView) findViewById(R.id.teamNameA);
        TextView nameB = (TextView) findViewById(R.id.teamNameB);
        
        // Get the message from the intent
        Intent intent = getIntent();
        gamer1 = intent.getStringExtra(PLAYER_1);
        gamer2 = intent.getStringExtra(PLAYER_2);

        nameA.setText(gamer1);
        nameB.setText(gamer2);

        // Welcome message
        Toast.makeText(this, getString(R.string.start_game_prepare) + gamer1 +
                getString(R.string.start_game_vs) + gamer2, Toast.LENGTH_LONG).show();
    }

    // Save state when rotating
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_SCORE_A, scoreTeamA);
        outState.putInt(STATE_SCORE_B, scoreTeamB);
        outState.putString(SAVE_NAME_A, gamer1);
        outState.putString(SAVE_NAME_B, gamer2);

        super.onSaveInstanceState(outState);

    }

    // Restore state when rotating
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        savedInstanceState.getInt(STATE_SCORE_A);
        savedInstanceState.getInt(STATE_SCORE_B);
        savedInstanceState.getString(SAVE_NAME_A);
        savedInstanceState.getString(SAVE_NAME_B);

        super.onRestoreInstanceState(savedInstanceState);
    }


    /**
     * Display the given score for the Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);

        if (scoreTeamA <= -1) {
            Toast.makeText(this, getString(R.string.msg_sucks) + gamer1, Toast.LENGTH_SHORT).show();
        }

        if (scoreTeamA >= 20) {
            Toast.makeText(this, getString(R.string.msg_legendary) + gamer1, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getString(R.string.msg_sucks) + gamer2, Toast.LENGTH_SHORT).show();
        }

        if (scoreTeamB >= 20) {
            Toast.makeText(this, getString(R.string.msg_legendary) + gamer2, Toast.LENGTH_SHORT).show();
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

        // Toast message for a new game
        Toast.makeText(this, getString(R.string.msg_again) + gamer1 + getString(R.string.msg_and) + gamer2,
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

            // Winner Player 1 Toast message
            Toast.makeText(this, winningPlayer + getString(R.string.winner_part_text),
                    Toast.LENGTH_LONG).show();

        } else if (scoreTeamA < scoreTeamB) {
            winningPlayer = gamer2;

            // Winner Player 2 Toast message
            Toast.makeText(this, winningPlayer + getString(R.string.winner_part_text),
                    Toast.LENGTH_LONG).show();

        } else {
            // Equal Result Toast message
            Toast.makeText(this, getString(R.string.msg_equal_score) + gamer1 +
                            getString(R.string.msg_and) + gamer2 + getString(R.string.msg_is_equal),
                    Toast.LENGTH_LONG).show();
        }

    }

}
