package com.example.android_asm1.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_asm1.R;
import com.example.android_asm1.activities.adapters.GameHistoryAdapter;
import com.example.android_asm1.models.game.GameHistory;
import com.example.android_asm1.models.game.GameHistoryManager;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements  GameHistoryAdapter.DeleteListener{


    private ListView listView;
    private GameHistoryAdapter adapter;
    private LinearLayout noHistoryContainer;
    private Button backButton;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        // Initialization
        listView = findViewById(R.id.historyList);
        noHistoryContainer = findViewById(R.id.noHistoryContainer);
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Back Button
        backButton.setOnClickListener(v -> {
            finishOk();
        });

        // Home Button
        homeButton.setOnClickListener(v -> {
            finishOk();
        });

        // Create and set the adapter
        ArrayList<GameHistory> gameHistories = GameHistoryManager.getHistoryList();
        if (gameHistories.isEmpty()) {
            showNoHistory();
            return;
        }

        showList();

        // Set adapter
        adapter = new GameHistoryAdapter(this, gameHistories, this);
        listView.setAdapter(adapter);
    }

    private void showNoHistory() {
        noHistoryContainer.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    private void showList() {
        noHistoryContainer.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    private void finishOk() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onDelete(int id) {
        GameHistoryManager.removeHistory(id);
        if (GameHistoryManager.isEmpty()) {
            showNoHistory();
        }
    }
}