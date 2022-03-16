package de.schule.firsttest.objs;

import java.util.HashMap;
import java.util.UUID;

public class Projekt {

    private UUID id;
    private String name;
    private HashMap<Integer, Zahlung> geordneteZahlungen;
    private double budget;

    public Projekt(String name, double budget) {
        id = UUID.randomUUID();
        this.name = name;
        geordneteZahlungen = new HashMap<>();
        this.budget = budget;
    }

    public HashMap<Integer, Zahlung> getGeordneteZahlungen() {
        return geordneteZahlungen;
    }

    public void eintragHinzufügen(int nummer,Zahlung z){
        z.setId(nummer);
        geordneteZahlungen.put(nummer,z);
    }

    public void eintragLöschen(int nummer){
        geordneteZahlungen.remove(nummer);
    }

    public Zahlung bekommeZahlung(int nummer){
        return geordneteZahlungen.get(nummer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
