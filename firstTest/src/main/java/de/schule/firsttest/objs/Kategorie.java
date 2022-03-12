package de.schule.firsttest.objs;

import java.util.UUID;

public class Kategorie {
    UUID id;
    String kategorieName;

    public Kategorie(UUID id, String kategorieName) {
        this.id = id;
        this.kategorieName = kategorieName;
    }
}
