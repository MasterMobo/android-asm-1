package com.example.android_asm1.models.painting;

import com.example.android_asm1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PaintingManager {
    static HashMap<Integer, Painting> paintings;

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
                "The Starry Night is an oil-on-canvas painting by the Dutch Post-Impressionist painter Vincent van Gogh, painted in June 1889. It depicts the view from the east-facing window of his asylum room at Saint-Rémy-de-Provence, just before sunrise, with the addition of an imaginary village. It has been in the permanent collection of the Museum of Modern Art in New York City since 1941, acquired through the Lillie P. Bliss Bequest. Widely regarded as Van Gogh's magnum opus, The Starry Night is one of the most recognizable paintings in Western art.",
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

        add(factory.createWithYear(
                "Girl with a Pearl Earring",
                "Johannes Vermeer",
                1665,
                "Girl with a Pearl Earring (Dutch: Meisje met de parel) is an oil painting by Dutch Golden Age painter Johannes Vermeer, dated c. 1665. Going by various names over the centuries, it became known by its present title towards the end of the 20th century because of the earring worn by the girl portrayed there. The work has been in the collection of the Mauritshuis in The Hague since 1902 and has been the subject of various literary and cinematic treatments.",
                R.drawable.girl_with_a_pearl_earring
        ));

        add(factory.createWithYearRange(
                "A Sunday Afternoon on the Island of La Grande Jatte",
                "Georges Seurat",
                1884,
                1886,
                "A Sunday Afternoon on the Island of La Grande Jatte (French: Un dimanche après-midi à l'Île de la Grande Jatte) was painted from 1884 to 1886 and is Georges Seurat's most famous work.A leading example of pointillist technique, executed on a large canvas, it is a founding work of the neo-impressionist movement. Seurat's composition includes a number of Parisians at a park on the banks of the River Seine. It is held in the collection of the Art Institute of Chicago.",
                R.drawable.la_grande_jatte
        ));

        add(factory.createWithYear(
                "The Arnolfini Portrait",
                "Jan van Eyck",
                1434,
                "The Arnolfini Portrait (or The Arnolfini Wedding, The Arnolfini Marriage, the Portrait of Giovanni Arnolfini and his Wife, or other titles) is an oil painting on oak panel by the Early Netherlandish painter Jan van Eyck, dated 1434 and now in the National Gallery, London. It is a full-length double portrait, believed to depict the Italian merchant Giovanni di Nicolao Arnolfini and his wife, presumably in their residence at the Flemish city of Bruges.",
                R.drawable.the_arnolfini_portrait
        ));

        add(factory.createWithYear(
                "Las Meninas",
                "Diego Velázquez",
                1656,
                "Las Meninas (Spanish for 'The Ladies-in-waiting'[a] pronounced [las meˈninas]) is a 1656 painting in the Museo del Prado in Madrid, by Diego Velázquez, the leading artist of the Spanish Baroque. It has become one of the most widely analyzed works in Western painting for the way its complex and enigmatic composition raises questions about reality and illusion, and for the uncertain relationship it creates between the viewer and the figures depicted.",
                R.drawable.las_meninas
        ));

        add(factory.createWithYear(
                "Guernica",
                "Pablo Picasso",
                1937,
                "Guernica is a large 1937 oil painting by Spanish artist Pablo Picasso.It is one of his best-known works, regarded by many art critics as the most moving and powerful anti-war painting in history. It is exhibited in the Museo Reina Sofía in Madrid.\nThe grey, black, and white painting, on a canvas 3.49 meters (11 ft 5 in) tall and 7.76 meters (25 ft 6 in) across, portrays the suffering wrought by violence and chaos. Prominently featured in the composition are a gored horse, a bull, screaming women, a dead baby, a dismembered soldier, and flames.",
                R.drawable.guernica
        ));

        add(factory.createWithYear(
                "Wanderer above the Sea of Fog",
                "Caspar David Friedrich",
                1818,
                "Wanderer above the Sea of Fog is a painting by German Romanticist artist Caspar David Friedrich made in 1818. It depicts a man standing upon a rocky precipice with his back to the viewer; he is gazing out on a landscape covered in a thick sea of fog through which other ridges, trees, and mountains pierce, which stretches out into the distance indefinitely.\n It has been considered one of the masterpieces of the Romantic movement and one of its most representative works. The painting has been interpreted as an emblem of self-reflection or contemplation of life's path, and the landscape is considered to evoke the sublime. Friedrich was a common user of Rückenfigur (German: Rear-facing figure) in his paintings; Wanderer above the Sea of Fog is perhaps the most famous Rückenfigur in art due to the subject's prominence. The painting has also been interpreted as an expression of Friedrich's German liberal and nationalist feeling. ",
                R.drawable.wanderer_above_the_sea_of_fog
        ));

        add(factory.createWithYear(
                "Nighthawks",
                "Edward Hopper",
                1982,
                "Nighthawks is a 1942 oil on canvas painting by the American artist Edward Hopper that portrays four people in a downtown diner late at night as viewed through the diner's large glass window. The light coming from the diner illuminates a darkened and deserted urban streetscape.\nThe painting has been described as Hopper's best-known work and is one of the most recognizable paintings in American art. Classified as part of the American Realism movement, within months of its completion, it was sold to the Art Institute of Chicago for $3,000.",
                R.drawable.nighthawks
        ));

        add(factory.createWithYearRange(
                "The Swing",
                "Jean-Honoré Fragonard",
                1767,
                1768,
                "The Swing (French: L'Escarpolette), also known as The Happy Accidents of the Swing (French: Les Hasards heureux de l'escarpolette, the original title), is an 18th-century oil painting by Jean-Honoré Fragonard in the Wallace Collection in London. It is considered to be one of the masterpieces of the Rococo era, and is Fragonard's best-known work.\nThe painting depicts an elegantly dressed young woman on a swing. A smiling young man, hiding in the bushes below and to the left, points towards her billowing dress with hat in hand. A smiling older man, who is nearly hidden in the shadows on the right, propels the swing with a pair of ropes, as a small white dog barks nearby. The lady is wearing a bergère hat (shepherdess hat), as she flings her shoe with an outstretched left foot. Two statues are present, one of a putto, who watches from above the young man on the left with its finger in front of its lips, the other of two putti is on the right beside the older man.",
                R.drawable.the_swing
        ));

        add(factory.createWithYear(
                "Café Terrace at Night",
                "Vincent van Gogh",
                1888,
                "Café Terrace at Night is an 1888 oil painting by the Dutch artist Vincent van Gogh. It is also known as The Cafe Terrace on the Place du Forum, and, when first exhibited in 1891, was entitled Coffeehouse, in the evening (Café, le soir).\nVan Gogh painted Café Terrace at Night in Arles, France, in mid-September 1888. The painting is not signed, but described and mentioned by the artist in three letters.[1]\n Visitors to the site can stand at the north eastern corner of the Place du Forum, where the artist set up his easel. The site was refurbished in 1990 and 1991 to replicate van Gogh's painting. He looked south towards the artificially lit terrace of the popular coffee house, as well as into the enforced darkness of the rue du Palais which led up to a building structure (to the left, not pictured) and, beyond this structure, the tower of a former church which is now Musée Lapidaire. ",
                R.drawable.cafe_terrace_at_night
        ));

        add(factory.createWithYear(
                "Woman With A Parasol - Madame Monet And Her Son",
                "Claude Monet",
                1875,
                "Woman with a Parasol – Madame Monet and Her Son, sometimes known as The Stroll (French: La Promenade) is an oil-on-canvas painting by Claude Monet from 1875. The Impressionist work depicts his wife Camille Monet and their son Jean Monet in the period from 1871 to 1877 while they were living in Argenteuil, capturing a moment on a stroll on a windy summer's day.\nMonet's light, spontaneous brushwork creates splashes of colour. Mrs Monet's veil is blown by the wind, as is her billowing white dress; the waving grass of the meadow is echoed by the green underside of her parasol. She is seen as if from below, with a strong upward perspective, against fluffy white clouds in an azure sky. A boy, Monet's seven-year-old son Jean, is placed further away, concealed behind a rise in the ground and visible only from the waist up, creating a sense of depth, the moment using animated brush strokes full of vibrant color.",
                R.drawable.woman_with_a_parasol
        ));

        add(factory.createWithYear(
                "Impression, Sunrise",
                "Claude Monet",
                1872,
                "Impression, Sunrise (French: Impression, soleil levant) is an 1872 painting by Claude Monet first shown at what would become known as the \"Exhibition of the Impressionists\" in Paris in April, 1874. The painting is credited with inspiring the name of the Impressionist movement.\nImpression, Sunrise depicts the port of Le Havre, Monet's hometown. It is usually displayed at the Musée Marmottan Monet but was on loan at the Musée d'Orsay from 26 March until 14 July 2024, and is currently at the National Gallery of Art from 8 September 2024 until 19 January 2025.",
                R.drawable.impression_sunrise
        ));

        add(factory.createWithYear(
                "Vase with Irises Against a Yellow Background",
                "Vincent van Gogh",
                1889,
                "Vase with Irises Against a Yellow Background is an oil painting on canvas made in 1889 by the painter Vincent Van Gogh. It is preserved in the Van Gogh Museum in Amsterdam. It is one of the works done while he was admitted to the psychiatric clinic in Saint-Rémy, a town near Arles.\nVan Gogh has a similar work, with the same name, but also known as Vase with Iris, located in the Metropolitan Museum of Art in New York.",
                R.drawable.irises
        ));

        add(factory.createWithYearRange(
                "Lady with an Ermine",
                "Leonardo da Vinci",
                1489,
                1491,
                "The Lady with an Ermine is a portrait painting by the Italian Renaissance artist Leonardo da Vinci. Dated to c.1489–1491, the work is painted in oils on a panel of walnut wood. Its subject is Cecilia Gallerani, a mistress of Ludovico Sforza (\"Il Moro\"), Duke of Milan; Leonardo was painter to the Sforza court in Milan at the time of its execution. It is the second of only four surviving portraits of women painted by Leonardo, the others being Ginevra de' Benci, La Belle Ferronnière and the Mona Lisa.\nLady with an Ermine is now housed at the Czartoryski Museum in Kraków, and is one of Poland's national treasures. It is part of the Princes Czartoryski Collection, which was sold for €100 million (5% of the estimated market value of the entire collection)[5] on 29 December 2016 to the Polish government by Princes Czartoryski Foundation, represented by Adam Karol Czartoryski, the last direct descendant of Izabela Czartoryska Flemming and Adam George Czartoryski, who brought the painting to Poland from Italy in 1798",
                R.drawable.lady_with_an_ermine
        ));

        add(factory.createWithYear(
                "The Night Watch",
                "Rembrandt van Rijn",
                1642,
                "Militia Company of District II under the Command of Captain Frans Banninck Cocq, also known as The Shooting Company of Frans Banning Cocq and Willem van Ruytenburch, but commonly referred to as The Night Watch (Dutch: De Nachtwacht), is a 1642 painting by Rembrandt van Rijn. It is in the collection of the Amsterdam Museum but is prominently displayed in the Rijksmuseum as the best-known painting in its collection. The Night Watch is one of the most famous Dutch Golden Age paintings. Rembrandt's large painting (363 by 437 centimetres (12 by 14+1⁄2 feet)) is famed for transforming a group portrait of a civic guard company into a compelling drama energized by light and shadow (tenebrism). The title is a misnomer; the painting does not depict a nocturnal scene.",
                R.drawable.the_night_watch
        ));

        add(factory.createWithYearRange(
                "The Milkmaid",
                "Johannes Vermeer",
                1657,
                1658,
                "The Milkmaid (Dutch: De melkmeid or Het melkmeisje), sometimes called The Kitchen Maid (Dutch: De keukenmeid), is an oil-on-canvas painting of a \"milkmaid\", in fact, a domestic kitchen maid, by the Dutch artist Johannes Vermeer. It is in the Rijksmuseum in Amsterdam, the Netherlands, which regards it as \"unquestionably one of the museum's finest attractions\".\nThe exact year of the painting's completion is unknown, with estimates varying by source. The Rijksmuseum estimates it as circa 1658. According to the Metropolitan Museum of Art in New York City, it was painted in about 1657 or 1658. The \"Essential Vermeer\" website gives a broader range of 1658–1661",
                R.drawable.the_milkmaid
        ));

        add(factory.createWithYear(
                "American Gothic",
                "Grant Wood",
                1930,
                "American Gothic is a 1930 painting by Grant Wood in the collection of the Art Institute of Chicago. A character study of a man and a woman portrayed in front of a home, American Gothic is one of the most famous American paintings of the 20th century, and has been widely parodied in American popular culture.\nWood was inspired to paint what is now known as the American Gothic House in Eldon, Iowa, along with \"the kind of people [he] fancied should live in that house\". It depicts a farmer standing beside his daughter – often mistakenly assumed to be his wife",
                R.drawable.american_gothic
        ));

        add(factory.createWithYear(
                "The Storm on the Sea of Galilee",
                "Rembrandt van Rijn",
                1633,
                "Christ in the Storm on the Sea of Galilee is a 1633 oil-on-canvas painting by the Dutch Golden Age painter Rembrandt van Rijn. It is classified as a history painting and is among the largest and earliest of Rembrandt's works. It was purchased by Bernard Berenson for Isabella Stewart Gardner in 1869 and was displayed at the Isabella Stewart Gardner Museum in Boston before its theft in 1990; it remains missing. The painting depicts the biblical event in which Jesus calmed the storm on the Sea of Galilee, as is described in the fourth chapter of the Gospel of Mark. It is Rembrandt's only seascape.",
                R.drawable.christ_in_the_storm
        ));

    }
}
