package com.example.android_asm1.models.painting;

import com.example.android_asm1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PaintingManager {
    static HashMap<Integer, Painting> paintings;

    private static void initPaintings() {
        paintings = new HashMap<>();
        PaintingFactory factory = new PaintingFactory();

        add(factory.createWithYearRange(
                "Mona Lisa",
                "Leonardo da Vinci",
                1503,
                1506,
                "The Mona Lisa is a half-length portrait painting by Italian artist Leonardo da Vinci. Considered an archetypal masterpiece of the Italian Renaissance, it has been described as \"the best known, the most visited, the most written about, the most sung about, [and] the most parodied work of art in the world.\" The painting's novel qualities include the subject's enigmatic expression, monumentality of the composition, the subtle modelling of forms, and the atmospheric illusionism",
                R.drawable.mona_lisa
        ));

        add(factory.createWithYear(
                "The Starry Night",
                "Vincent van Gogh",
                1889,
                "The Starry Night is an oil-on-canvas painting by the Dutch Post-Impressionist painter Vincent van Gogh, painted in June 1889. It depicts the view from the east-facing window of his asylum room at Saint-Rémy-de-Provence, just before sunrise, with the addition of an imaginary village.[1][2][3] It has been in the permanent collection of the Museum of Modern Art in New York City since 1941,[4] acquired through the Lillie P. Bliss Bequest. Widely regarded as Van Gogh's magnum opus,[5] The Starry Night is one of the most recognizable paintings in Western art.",
                R.drawable.starry_night
        ));

        add(factory.createWithYearRange(
                "The Kiss",
                "Gustav Klimt",
                1907,
                1908,
                "The Kiss is an oil-on-canvas painting with added gold leaf, silver and platinum by the Austrian Symbolist painter Gustav Klimt. It was painted at some point in 1907 and 1908, during the height of what scholars call his \"Golden Period\". It was exhibited in 1908 under the title Liebespaar (the lovers) as stated in the catalogue of the exhibition. The painting depicts a couple embracing each other, their bodies entwined in elaborate robes decorated in a style influenced by the contemporary Art Nouveau style and the organic forms of the earlier Arts and Crafts movement. ",
                R.drawable.the_kiss
        ));

        add(factory.createWithYear(
                "The Scream",
                "Edvard Munch",
                1893,
                "The Scream is a composition created by Norwegian artist Edvard Munch in 1893. The Norwegian name of the piece is Skrik (Scream), and the German title under which it was first exhibited is Der Schrei der Natur (The Scream of Nature). The agonized face in the painting has become one of the most iconic images in art, seen as symbolizing the anxiety of the human condition. Munch's work, including The Scream, had a formative influence on the Expressionist movement.",
                R.drawable.the_scream
        ));

        add(factory.createWithYear(
                "Napoleon Crossing the Alps",
                "Jacques-Louis David",
                1801,
                "Napoleon Crossing the Alps (also known as Napoleon at the Saint-Bernard Pass or Bonaparte Crossing the Alps; listed as Le Premier Consul franchissant les Alpes au col du Grand Saint-Bernard) is a series of five oil on canvas equestrian portraits of Napoleon Bonaparte painted by the French artist Jacques-Louis David between 1801 and 1805. Initially commissioned by the King of Spain, the composition shows a strongly idealized view of the real crossing that Napoleon and his army made along the Alps through the Great St Bernard Pass in May 1800.",
                R.drawable.napoleon_crossing_the_alps
        ));
    }

    private static void add(Painting painting) {
        getPaintings().put(painting.getId(), painting);
    }

    public static ArrayList<Painting> getPaintingList() {
        return new ArrayList<>(getPaintings().values());
    }

    public static ArrayList<Painting> getUnlockedList() {
        ArrayList<Painting> unlockedPaintings = new ArrayList<>();

        for (Painting p : getPaintings().values()) {
            if (p.isUnlocked()) {
                unlockedPaintings.add(p);
            }
        }

        return unlockedPaintings;
    }

    public static Painting getPainting(int id) {
        return getPaintings().get(id);
    }

    public static int getTotalSize() {
        return paintings.size();
    }

    private static HashMap<Integer, Painting> getPaintings() {
        if (paintings == null) {
            initPaintings();
        }
        return paintings;
    }
}
