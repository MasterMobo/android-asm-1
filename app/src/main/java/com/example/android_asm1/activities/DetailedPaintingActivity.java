package com.example.android_asm1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_asm1.R;
import com.example.android_asm1.models.painting.Painting;
import com.example.android_asm1.models.painting.PaintingManager;

public class DetailedPaintingActivity extends AppCompatActivity {
    private ImageView paintingImage;
    private TextView paintingName;
    private TextView paintingYear;
    private TextView artistName;
    private TextView paintingDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_painting);

        Intent intent = getIntent();
        int paintingId = intent.getIntExtra("paintingId", -1);

        if (paintingId == -1) {
            Log.e("ERROR", "Invalid painting id");
            setResult(RESULT_CANCELED);
            finish();
        }

        Painting painting = PaintingManager.getPainting(paintingId);

        if (painting == null) {
            Log.e("ERROR", "Painting not found");
            setResult(RESULT_CANCELED);
            finish();
        }

        paintingImage = findViewById(R.id.detailedPaintingImage);
        paintingName = findViewById(R.id.paintingName);
        paintingYear = findViewById(R.id.paintingYear);
        artistName = findViewById(R.id.artistName);
        paintingDescription = findViewById(R.id.paintingDescription);

        paintingImage.setImageResource(painting.getResourceId());
        paintingName.setText(painting.getDisplayName());
        paintingYear.setText(painting.getDisplayYear());
        artistName.setText(painting.getArtistLabel());
        paintingDescription.setText(painting.getDescription());
    }
}