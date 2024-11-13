package com.example.android_asm1.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_asm1.R;
import com.example.android_asm1.models.painting.Painting;

import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Painting> paintings;
    private OnClickListener onClickListener;

    public GalleryAdapter(@NonNull Context context, ArrayList<Painting> paintings, OnClickListener onClickListener) {
        this.context = context;
        this.paintings = paintings;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return paintings.size();
    }

    @Override
    public Object getItem(int position) {
        return paintings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);
        }

        Painting painting = paintings.get(position);
        ImageView galleryImage = convertView.findViewById(R.id.galleryImage);
        TextView galleryTitle = convertView.findViewById(R.id.galleryTitle);
        ImageView favoriteHeart = convertView.findViewById(R.id.favoriteHeart);

        galleryImage.setImageResource(painting.getResourceId());
        galleryTitle.setText(painting.getDisplayName());
        if (painting.isFavorite()) {
            favoriteHeart.setVisibility(View.VISIBLE);
        } else {
            favoriteHeart.setVisibility(View.GONE);
        }

        galleryImage.setOnClickListener(v -> {
            onClickListener.onClick(painting.getId());
        });

        return convertView;
    }

    public interface OnClickListener {
        void onClick(int id);
    }
}
