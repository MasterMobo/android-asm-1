package com.example.android_asm1.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_asm1.R;
import com.example.android_asm1.controllers.GameController;

public class GameActivity extends AppCompatActivity {
    // UI
    public TextView playerNameView;
    public String playerName;
    public TextView roundCounter;
    public TextView pointsView;
    public ImageView gameImage;

    public EditText yearInput;

    public Button submitButton;
    public LinearLayout answerContainer;
    public Button nextButton;
    public TextView answerYear;
    public TextView answerPaintingName;
    public TextView answerArtistName;
    public TextView answerDescription;
    private Button backButton;
    private Button homeButton;

    AlertDialog gameEndDialog;

    // Controller
    GameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        // Initialization
        controller = new GameController(this);
        playerNameView = findViewById(R.id.playerName);
        roundCounter = findViewById(R.id.roundCounter);
        pointsView = findViewById(R.id.pointsContent);
        gameImage = findViewById(R.id.paintingImage);
        yearInput = findViewById(R.id.yearInput);
        submitButton = findViewById(R.id.submit);
        answerContainer = findViewById(R.id.answerContainer);
        nextButton = findViewById(R.id.next);
        answerYear = findViewById(R.id.answerYear);
        answerPaintingName = findViewById(R.id.answerPaintingName);
        answerArtistName = findViewById(R.id.answerArtistName);
        answerDescription = findViewById(R.id.answerDescription);
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Get intent data
        Intent intent = getIntent();
        playerName = intent.getStringExtra("playerName");

        // Submit Button wiring
        submitButton.setOnClickListener(v -> {
            controller.submitGuess();
        });

        // Next Button wiring
        nextButton.setOnClickListener(v -> {
            controller.advanceGame();
        });

        // Back Button
        backButton.setOnClickListener(v -> {
            finishOk();
        });

        // Home Button
        homeButton.setOnClickListener(v -> {
            finishOk();
        });

        // Start game after initialization
        controller.startGame(playerName);

    }

    public void showEndScreen(int points) {
        gameEndDialog = createGameEndDialog(points);
        gameEndDialog.show();
    }

    private void finishOk() {
        setResult(RESULT_OK);
        finish();
    }

    AlertDialog createGameEndDialog(int points) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Game Over! You got " + points + " points");
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controller.startGame(playerName);
            }
        });
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(RESULT_OK);
                finish();
            }
        });

        return builder.create();
    }
}
