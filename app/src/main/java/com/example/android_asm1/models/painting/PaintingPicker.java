package com.example.android_asm1.models.painting;

import java.util.ArrayList;
import java.util.Random;

public class PaintingPicker {
    ArrayList<Painting> paintings;
    Random random;

    public PaintingPicker(ArrayList<Painting> paintings) {
        this.paintings = paintings;
        this.random = new Random();
    }

    // Create a randomly selected array of length count from the list of paintings
    public ArrayList<Painting> getRandomPaintings(int count) {
        ArrayList<Painting> selectionPool = (ArrayList<Painting>) paintings.clone();
        ArrayList<Painting> res = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(selectionPool.size());
            res.add(selectionPool.get(randomIndex));
            selectionPool.remove(randomIndex);
        }

        return res;
    }
}
