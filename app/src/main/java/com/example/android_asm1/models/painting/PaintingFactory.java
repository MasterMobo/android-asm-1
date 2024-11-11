package com.example.android_asm1.models.painting;

public class PaintingFactory {
    public Painting createWithYear(String displayName, String artist, int yearStart, String description, int resourceId) {
        return new Painting(
                displayName,
                artist,
                String.valueOf(yearStart),
                yearStart,
                yearStart,
                description,
                resourceId
        );
    }

    public Painting createWithYearRange(String displayName, String artist, int yearStart, int yearEnd, String description, int resourceId) {
        return new Painting(
                displayName,
                artist,
                yearStart + "-" + yearEnd,
                yearStart,
                yearEnd,
                description,
                resourceId
        );
    }
}
