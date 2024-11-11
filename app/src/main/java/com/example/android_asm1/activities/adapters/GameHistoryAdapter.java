package com.example.android_asm1.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.android_asm1.R;
import com.example.android_asm1.models.game.GameHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GameHistoryAdapter extends ArrayAdapter<GameHistory> {

    private Context context;
    private ArrayList<GameHistory> gameHistoryList;
    private DeleteListener deleteListener;

    public GameHistoryAdapter(@NonNull Context context, ArrayList<GameHistory> gameHistoryList, DeleteListener deleteListener) {
        super(context, 0, gameHistoryList);
        this.context = context;
        this.gameHistoryList = gameHistoryList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_game_history, parent, false);
        }

        GameHistory gameHistory = getItem(position);

        TextView playerNameTextView = convertView.findViewById(R.id.player_name);
        TextView timeTextView = convertView.findViewById(R.id.time);
        TextView pointsTextView = convertView.findViewById(R.id.points);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        playerNameTextView.setText(gameHistory.getPlayerName());
        timeTextView.setText(formatDate(gameHistory.getTime()));
        pointsTextView.setText(String.valueOf(gameHistory.getPoints() + " Points"));

        deleteButton.setOnClickListener(v -> {
            // Notify listener
            if (deleteListener != null) {
                deleteListener.onDelete(gameHistory.getId());
            }

            // Remove item from list
            gameHistoryList.remove(position);

            // Update list view to reflect change
            notifyDataSetChanged();
        });

        return convertView;
    }

    // Helper method to format the date
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm  dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }

    public interface DeleteListener {
        void onDelete(int id);
    }
}
