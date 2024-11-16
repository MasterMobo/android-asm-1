package com.example.android_asm1.models.game;

import com.example.android_asm1.controllers.GameController;
import com.example.android_asm1.models.painting.Painting;
import com.example.android_asm1.models.painting.PaintingManager;
import com.example.android_asm1.models.painting.PaintingPicker;

import java.util.ArrayList;
import java.util.Date;

public class GameModel {
    // MVC pattern
    // This class represents a single game

    private String playerName;                  // Name of the player
    private int points;                         // Current points of the game
    private ArrayList<Painting> paintings;      // A list of paintings for the game. Randomly selected with length of rounds when game starts
    private int currentPaintingIndex = 0;       // Index of the current painting in the paintings list
    private int rounds = 5;                     // Number of rounds in the game

    private GameController controller;

    public GameModel() {
    }

    public GameModel(GameController controller) {
        this();
        this.controller = controller;
    }

    // Starts the game
    // Reset (or initialize) game attributes, and pick new random paintings
    public void startGame(String playerName) {
        this.playerName = playerName;
        points = 0;
        currentPaintingIndex = 0;

        PaintingPicker paintingPicker = new PaintingPicker(PaintingManager.getPaintingList());

        paintings = paintingPicker.getRandomPaintings(rounds);

        controller.onGameStarted();
    }

    // Processes guess of player
    // Increment points based on guess accuracy and unlocks new paintings
    public void submitGuess(int year) {
        Painting answer = getCurrentPainting();

        points += calculateScore(answer.getYearStart(), answer.getYearEnd(), year);
        answer.unlock();

        controller.onGuessSubmitted();
    }

    // Advances game to next round
    // If game is over, calls finishGame()
    public void advance() {
        currentPaintingIndex++;

        if (currentPaintingIndex >= paintings.size()) {
            finishGame();
            return;
        }

        controller.onGameAdvanced();
    }

    // Finishes the game
    // Add game to history
    public void finishGame() {
        GameHistory history = new GameHistory(playerName, points, new Date());
        GameHistoryManager.addHistory(history);

        controller.onGameFinished();
    }

    public Painting getCurrentPainting() {
        return paintings.get(currentPaintingIndex);
    }

    public int getPoints() {
        return points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getTotalRounds() {
        return rounds;
    }

    public int getCurrentRound() {
        return currentPaintingIndex + 1;
    }

    // Calculates the score based on proximity
    private int calculateScore(int targetYearStart, int targetYearEnd, int userGuess) {

        // If the guess is within the range, award 1000 points
        if (targetYearStart <= userGuess && userGuess <= targetYearEnd) {
            return 1000;
        }

        // If user under-guessed, calculate based on targetYearStart
        if (userGuess < targetYearStart) {
            return calculateScore(targetYearStart, userGuess);
        }

        // If user over-guessed, calculate based on targetYearEnd
        return calculateScore(targetYearEnd, userGuess);
    }

    // Calculates the score based on proximity
    public static int calculateScore(int targetYear, int userGuess) {
        // If the guess is the exact year, award 1000 points
        if (targetYear == userGuess) {
            return 1000;
        }

        // Calculate the century of the target year and the user's guess
        int targetCentury = targetYear / 100;
        int guessCentury = userGuess / 100;

        // If the guess is within the same century (within 100 years), award 100 points
        if (targetCentury == guessCentury) {
            // Check if it's within the same decade (same range of 10 years)
            if ((userGuess / 10) == (targetYear / 10)) {
                return 500;  // Same decade
            }
            return 100; // Same century but not the same decade
        }

        // If not in the same century, return 0 points (no match)
        return 0;
    }
}
