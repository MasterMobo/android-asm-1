package com.example.android_asm1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_asm1.R;
import com.example.android_asm1.models.painting.Painting;
import com.example.android_asm1.models.painting.PaintingManager;

public class DetailedPaintingActivity extends AppCompatActivity {
    private ImageView paintingImage;
    private TextView paintingName;
    private TextView paintingYear;
    private TextView artistName;
    private TextView paintingDescription;
    private ImageView favoriteHeart;
    private ImageView noFavoriteHeart;
    private Painting painting;
    private TextView headerLabel;
    private Button backButton;
    private Button homeButton;
    private boolean galleryUpdateNeeded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_painting);

        // Get painting ID from intent
        Intent intent = getIntent();
        int paintingId = intent.getIntExtra("paintingId", -1);

        // Missing intent data
        if (paintingId == -1) {
            Log.e("ERROR", "Invalid painting id");
            setResult(RESULT_CANCELED);
            finish();
        }

        painting = PaintingManager.getPainting(paintingId);

        // Invalid painting ID
        if (painting == null) {
            Log.e("ERROR", "Painting not found");
            setResult(RESULT_CANCELED);
            finish();
        }

        // Initialization
        paintingImage = findViewById(R.id.detailedPaintingImage);
        paintingName = findViewById(R.id.paintingName);
        paintingYear = findViewById(R.id.paintingYear);
        artistName = findViewById(R.id.artistName);
        paintingDescription = findViewById(R.id.paintingDescription);
        favoriteHeart = findViewById(R.id.favoriteHeart);
        noFavoriteHeart = findViewById(R.id.noFavoriteHear);
        headerLabel = findViewById(R.id.headerLabel);
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        paintingImage.setImageResource(painting.getResourceId());
        headerLabel.setText(painting.getDisplayName());
        paintingName.setText(painting.getDisplayName());
        paintingYear.setText(painting.getDisplayYear());
        artistName.setText(painting.getArtistLabel());
        paintingDescription.setText(painting.getDescription());

        backButton.setOnClickListener(v -> {
            finishAndUpdateGallery();
        });

        homeButton.setOnClickListener(v -> {
            setResult(GalleryActivity.SKIP_ON_FINISH);
            finish();
        });

        favoriteHeart.setOnClickListener(v -> {
            painting.setFavourite(false);
            galleryUpdateNeeded = true;
            updateFavoriteStatus();
        });

        noFavoriteHeart.setOnClickListener(v -> {
            painting.setFavourite(true);
            galleryUpdateNeeded = true;
            updateFavoriteStatus();
        });

        updateFavoriteStatus();


        // Custom back press callback to notify gallery of changes
        // Avoid unreflected changes when user presses the OS' back button instead of the UIs
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAndUpdateGallery();
            }
        };

        // Add the callback to the OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void updateFavoriteStatus() {
        if (painting.isFavorite()) {
            showFavorite();
        } else {
            showNotFavorite();
        }
    }

    private void showFavorite() {
        favoriteHeart.setVisibility(View.VISIBLE);
        noFavoriteHeart.setVisibility(View.GONE);
    }

    private void showNotFavorite() {
        favoriteHeart.setVisibility(View.GONE);
        noFavoriteHeart.setVisibility(View.VISIBLE);
    }


    private void finishAndUpdateGallery() {
        if (galleryUpdateNeeded) {
            setResult(GalleryActivity.UPDATE_NEEDED);
        } else {
            setResult(RESULT_OK);
        }
        finish();
    }

}