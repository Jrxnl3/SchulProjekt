package de.schule.firsttest.objs;

import java.util.HashMap;
import java.util.UUID;

public class Projekt {

    UUID id;
    String name;
    HashMap<Integer, Zahlung> geordneteZahlungen;

    public Projekt(String name) {
        id = UUID.randomUUID();
        this.name = name;
        geordneteZahlungen = new HashMap<>();
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
}
