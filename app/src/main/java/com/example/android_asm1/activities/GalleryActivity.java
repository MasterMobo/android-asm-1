package com.example.android_asm1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_asm1.R;
import com.example.android_asm1.activities.adapters.GalleryAdapter;
import com.example.android_asm1.models.painting.Painting;
import com.example.android_asm1.models.painting.PaintingManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.OnClickListener {
    // RESULT CODES
    // Launch detailed painting activity
    public static int LAUNCH_DETAIL = 102;

    // Need to update adapter to reflect changes.
    // Used when user favorite/unfavorite a painting from the detailed activity
    public static int UPDATE_NEEDED = 505;

    // Need to skip this activity and finish immediately
    public static int SKIP_ON_FINISH = 403;

    private TextView unlockedCount;
    private LinearLayout noPaintingsContainer;
    private LinearLayout galleryContainer;
    private GridView gridView;
    private GalleryAdapter adapter;
    private ArrayList<Painting> paintings;
    private Button backButton;
    private Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);

        // Initialization
        unlockedCount = findViewById(R.id.unlockedCount);
        gridView = findViewById(R.id.gridView);
        noPaintingsContainer = findViewById(R.id.noPaintingsContainer);
        galleryContainer = findViewById(R.id.galleryContainer);
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


        paintings = PaintingManager.getUnlockedList();

        if (paintings.isEmpty()) {
            showNoPaintings();
            return;
        };

        showGallery();

        String unlockedCountString = paintings.size() + "/" + PaintingManager.getTotalSize() + " Paintings Unlocked";
        unlockedCount.setText(unlockedCountString);

        updateGridView();
    }

    private void updateGridView() {
        paintings = PaintingManager.getUnlockedList();

        // Sort by favorite status
        paintings.sort((p1, p2) -> {
            // Prioritize favorite
            if (p1.isFavorite()) return -1;
            if (p2.isFavorite()) return 1;

            // If both are not favorite, compare names
            return p1.getDisplayName().compareTo(p2.getDisplayName());
        });

        adapter = new GalleryAdapter(this, paintings, this);
        gridView.setAdapter(adapter);
    }

    private void showNoPaintings() {
        noPaintingsContainer.setVisibility(View.VISIBLE);
        galleryContainer.setVisibility(View.GONE);
    }

    private void showGallery() {
        noPaintingsContainer.setVisibility(View.GONE);
        galleryContainer.setVisibility(View.VISIBLE);
    }

    private void finishOk() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent(GalleryActivity.this, DetailedPaintingActivity.class);
        intent.putExtra("paintingId", id);
        startActivityForResult(intent, LAUNCH_DETAIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_DETAIL) {
            if (resultCode == UPDATE_NEEDED) {
                updateGridView();
                return;
            }

            if (resultCode == SKIP_ON_FINISH) {
                finishOk();
            }
        }
    }
}