package com.example.android_asm1.controllers;

import android.view.View;
import android.widget.Toast;

import com.example.android_asm1.activities.GameActivity;
import com.example.android_asm1.models.game.GameModel;
import com.example.android_asm1.models.painting.Painting;

public class GameController {
    // MVC pattern
    // Forward UI interactions to GameModel and control game UI updates from GameActivity

    private GameModel model;        // Model
    private GameActivity activity;  // View

    public GameController(GameActivity activity) {
        this.activity = activity;
        model = new GameModel(this);
    }

    public void startGame(String playerName) {
           model.startGame(playerName);
    }

    public void onGameStarted() {
        Painting painting = model.getCurrentPainting();

        // Header
        activity.playerNameView.setText(model.getPlayerName());
        updateRoundCounter();
        activity.pointsView.setText("0");

        // Game image
        activity.gameImage.setImageResource(painting.getResourceId());

        // Show submit button
        activity.submitButton.setVisibility(View.VISIBLE);

        // Set up answer section
        setupAnswer(painting);
    }

    public void submitGuess() {
        String yearGuessString = activity.yearInput.getText().toString();

        // Check empty string
        if (yearGuessString.isEmpty()) {
            Toast.makeText(activity, "Please guess a year!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check valid int string
        int yearGuess;
        try {
            yearGuess = Integer.parseInt(yearGuessString);
        } catch (NumberFormatException e) {
            Toast.makeText(activity, "Please enter a valid year!", Toast.LENGTH_SHORT).show();
            return;
        }

        model.submitGuess(yearGuess);
    }

    public void onGuessSubmitted() {
        // Update points
        activity.pointsView.setText(String.valueOf(model.getPoints()));

        // Hide submit button
        activity.submitButton.setVisibility(View.GONE);

        // Show answer section
        activity.answerContainer.setVisibility(View.VISIBLE);
    }

    public void advanceGame() {
        model.advance();
    }

    public void onGameAdvanced() {
        Painting painting = model.getCurrentPainting();

        updateRoundCounter();

        // Update game image
        activity.gameImage.setImageResource(painting.getResourceId());

        // Clear input
        activity.yearInput.setText("");

        // Show submit button
        activity.submitButton.setVisibility(View.VISIBLE);

        // Set up answer section
        setupAnswer(painting);
    }

    public void onGameFinished() {
        activity.showEndScreen(model.getPoints());
    }

    private void setupAnswer(Painting painting) {
        activity.answerContainer.setVisibility(View.GONE);
        activity.answerYear.setText(painting.getDisplayYear());
        activity.answerPaintingName.setText(painting.getDisplayName());
        activity.answerArtistName.setText(painting.getArtistLabel());
        activity.answerDescription.setText(painting.getDescription());
    }

    private void updateRoundCounter() {
        activity.roundCounter.setText("Round: " + model.getCurrentRound() + "/" + model.getTotalRounds());
    }
}
