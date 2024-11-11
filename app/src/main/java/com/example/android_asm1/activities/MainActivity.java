package com.example.android_asm1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_asm1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Player name
        EditText playerNameInput = findViewById(R.id.playerNameInput);

        // Play button
        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            String playerName = playerNameInput.getText().toString();

            if (playerName.isEmpty()) {
                Toast.makeText(this, "Please provide a player name!", Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("playerName", playerName);
            startActivity(intent);
        });

        // History Button
        Button historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        });

        // Gallery Button
        Button galleryButton = findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        });

    }
}