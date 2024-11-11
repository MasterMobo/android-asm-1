package com.example.android_asm1.models.game;

import java.util.ArrayList;
import java.util.HashMap;

public class GameHistoryManager {
    static HashMap<Integer, GameHistory> histories;

    private static HashMap<Integer, GameHistory> getHistories() {
        if (histories == null) {
            histories = new HashMap<>();
        }

        return histories;
    }

    public static void addHistory(GameHistory history) {
        getHistories().put(history.getId(), history);
    }

    public static void removeHistory(int id) {
        getHistories().remove(id);
    }

    public static ArrayList<GameHistory> getHistoryList() {
        return new ArrayList<>(getHistories().values());
    }

    public static boolean isEmpty() {
        return histories.isEmpty();
    }
}
