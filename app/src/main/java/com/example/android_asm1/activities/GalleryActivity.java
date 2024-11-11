package com.example.android_asm1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

    private TextView unlockedCount;
    private GridView gridView;
    private GalleryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);

        unlockedCount = findViewById(R.id.unlockedCount);
        gridView = findViewById(R.id.gridView);

        ArrayList<Painting> paintings = PaintingManager.getUnlockedList();

        // TODO: handle empty
        if (paintings.isEmpty()) return;

        String unlockedCountString = paintings.size() + "/" + PaintingManager.getTotalSize() + " Paintings Unlocked";
        unlockedCount.setText(unlockedCountString);

        adapter = new GalleryAdapter(this, paintings, this);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent(GalleryActivity.this, DetailedPaintingActivity.class);
        intent.putExtra("paintingId", id);
        startActivity(intent);
    }
}