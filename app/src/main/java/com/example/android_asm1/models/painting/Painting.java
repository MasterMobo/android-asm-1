package com.example.android_asm1.models.painting;

import java.util.Objects;

public class Painting {
    private static int ID_COUNTER = 0;
    private int id;
    private String displayName;
    private String artist;

    // Some paintings have unspecific year of creation, but instead a range estimate.
    // For paintings with a specific year, yearStart equals yearEnd
    private String displayYear;     // Year that is displayed to user
    private int yearStart;          // Start of correct year range
    private int yearEnd;            // End of correct year range

    private String description;
    private int resourceId;
    private boolean unlocked = false;
    private boolean favourite = false;

    public Painting() {
    }


    public Painting(String displayName, String artist, String displayYear, int yearStart, int yearEnd, String description, int resourceId) {
        this.id = ID_COUNTER++;
        this.displayName = displayName;
        this.artist = artist;
        this.displayYear = displayYear;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.description = description;
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Painting painting = (Painting) o;
        return id == painting.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getArtist() {
        return artist;
    }

    public String getArtistLabel() {
        return "by " + artist;
    }

    public String getDisplayYear() {
        return displayYear;
    }

    public int getYearStart() {
        return yearStart;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public String getDescription() {
        return description;
    }

    public int getResourceId() {
        return resourceId;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void unlock() {
        unlocked = true;
    }

    public boolean isFavorite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

}
