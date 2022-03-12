package de.schule.firsttest.objs;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Zahlung implements Serializable {
        UUID id;
        BetragsEnum operator;
        double betrag;
        Kategorie kategorie;
        String grund;
        Date datum;

    public Zahlung(UUID id, BetragsEnum operator, double betrag, Kategorie kategorie, String grund, Date datum) {
        this.id = id;
        this.operator = operator;
        this.betrag = betrag;
        this.kategorie = kategorie;
        this.grund = grund;
        this.datum = datum;
    }

    public Zahlung(BetragsEnum operator, double betrag, Kategorie kategorie, String grund, Date datum) {
        this.id = UUID.randomUUID();
        this.operator = operator;
        this.betrag = betrag;
        this.kategorie = kategorie;
        this.grund = grund;
        this.datum = datum;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BetragsEnum getOperator() {
        return operator;
    }

    public void setOperator(BetragsEnum operator) {
        this.operator = operator;
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Zahlung{" +
                "id=" + id +
                ", operator=" + operator +
                ", betrag=" + betrag +
                ", kategorie=" + kategorie +
                ", grund='" + grund + '\'' +
                ", datum=" + datum +
                '}';
    }
}
