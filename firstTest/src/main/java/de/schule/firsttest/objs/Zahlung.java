package de.schule.firsttest.objs;

import java.io.Serializable;
import java.time.LocalDate;

public class Zahlung implements Serializable {
        private int id;
        private double betrag;
        private Kategorie kategorie;
        private String kategorieName;
        private String grund;
        private String datum;

    public Zahlung(int id, double betrag, Kategorie kategorie, String grund, String datum) {
        this.id = 0;
        this.betrag = betrag;
        this.kategorie = kategorie;
        this.kategorieName = kategorie.getKategorieName();
        this.grund = grund;
        this.datum = datum;
    }

    public Zahlung(double betrag, Kategorie kategorie, String grund, String datum) {
        this.id = 0;
        this.betrag = betrag;
        this.kategorie = kategorie;
        this.kategorieName = kategorie.getKategorieName();
        this.grund = grund;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKategorieName() {
        return kategorieName;
    }

    public void setKategorieName(String kategorieName) {
        this.kategorieName = kategorieName;
    }

}
