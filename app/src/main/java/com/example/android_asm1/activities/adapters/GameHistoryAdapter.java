package com.example.android_asm1.activities.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.android_asm1.R;
import com.example.android_asm1.models.game.GameHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GameHistoryAdapter extends ArrayAdapter<GameHistory> {

    private Context context;                        // Context where this is rendered
    private ArrayList<GameHistory> gameHistoryList; // List of GameHistory to be rendered
    private DeleteListener deleteListener;          // Listener to notify when delete happens
    private AlertDialog deleteDialog;               // Dialog to confirm/cancel delete

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

        // Initialization
        TextView playerNameTextView = convertView.findViewById(R.id.player_name);
        TextView timeTextView = convertView.findViewById(R.id.time);
        TextView pointsTextView = convertView.findViewById(R.id.points);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        // Get current item
        GameHistory gameHistory = getItem(position);

        // Set correct view attributes
        playerNameTextView.setText(gameHistory.getPlayerName());
        timeTextView.setText(formatDate(gameHistory.getTime()));
        pointsTextView.setText(String.valueOf(gameHistory.getPoints() + " Points"));

        // Delete button wiring
        deleteButton.setOnClickListener(v -> {
            showDeleteDialog(gameHistory.getId());
        });

        return convertView;
    }

    // Helper method to format the date
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm  dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }

    // Listener to notify when delete happens
    public interface DeleteListener {
        // Called when a delete on item with id is triggered
        void onDelete(int id);
    }

    // Displays the delete dialog
    private void showDeleteDialog(int id) {
        deleteDialog = createDeleteDialog(context, id);
        deleteDialog.show();
    }

    // Called when user confirms a delete
    private void onDeleteConfirm(int id) {
        if (deleteListener == null) return;

        // Notify listener
        deleteListener.onDelete(id);

        // Remove item from list
        gameHistoryList.removeIf(history -> history.getId() == id);

        // Update list view to reflect change
        notifyDataSetChanged();
    }

    // Creates a new delete dialog
    AlertDialog createDeleteDialog(Context context, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Are you sure you want to delete this record?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onDeleteConfirm(id);
                deleteDialog.hide();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDialog.hide();
            }
        });

        return builder.create();
    }
}
