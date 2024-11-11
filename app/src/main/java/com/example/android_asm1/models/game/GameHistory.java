package com.example.android_asm1.models.game;

import java.util.Date;

public class GameHistory {
    private static int ID_COUNTER;
    private int id;
    private String playerName = "";
    private Date time;
    private int points;

    public GameHistory() {
    }

    public GameHistory(String playerName, int points, Date time) {
        this.id = ID_COUNTER++;
        this.playerName = playerName;
        this.points = points;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Date getTime() {
        return time;
    }

    public int getPoints() {
        return points;
    }
}
