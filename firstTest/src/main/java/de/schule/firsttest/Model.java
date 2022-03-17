package de.schule.firsttest;

import de.schule.firsttest.objs.Kategorie;
import de.schule.firsttest.objs.Projekt;
import de.schule.firsttest.objs.Zahlung;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.util.*;

public class Model {
    private ArrayList<Projekt> alleProjekte;

    public Model() {
        alleProjekte = new ArrayList<>();
    }

    //TODO: Entferne Test Variabeln
    public void testVariabeln(){
        //Erstelle ersteZahlung
        Zahlung ersteZahlung = new Zahlung(100.00,new Kategorie("Sprit"),"Warum net?",LocalDate.of(2022,1,5));
        Zahlung zweiteZahlung = new Zahlung(-300,new Kategorie("Essen"),"Hmmm geil",LocalDate.of(2020,12,1));
        Zahlung dritteZahlung = new Zahlung(-5,new Kategorie("Kaugumi"),"Worth it", LocalDate.of(2004,2,23));


        //Erstelle Projekt + eintrag Hinzufügen
        Projekt p1 = new Projekt("Test Projekt",500);
        p1.eintragHinzufügen(0,ersteZahlung);

        Projekt p2 = new Projekt("Zweit Projekt",100);
        p2.eintragHinzufügen(0,ersteZahlung);
        p2.eintragHinzufügen(1,zweiteZahlung);
        p2.eintragHinzufügen(3,dritteZahlung);

        alleProjekte.add(p1);
        alleProjekte.add(p2);
    }

    //TODO: Projekte laden @Jakub
    public ArrayList<Projekt> loadProjekte(String dateiName) {
        return alleProjekte;
    }

    //TODO: Projekte speichern @Jakub
    public void saveProjekte(ArrayList<Projekt> projekte){

    }

    public ArrayList<Projekt> getProjekte() {
        return alleProjekte;
    }

    public Projekt findProjekt(String projektName){
        for (Projekt p: alleProjekte) {
            if(p.getName().equals(projektName))
                return p;
        }
        return null;
    }

    public LinkedList<Zahlung> sortiereZahlung(Map<Integer, Zahlung> unsortedZahlungen){
        LinkedList<Zahlung> sortedZahlung = new LinkedList<>();

        for (int z = 0; z <= getMaxKey(unsortedZahlungen); z++) {
            sortedZahlung.add(unsortedZahlungen.get(z));
        }
        return sortedZahlung;
    }

    public Integer getMaxKey(Map<Integer, Zahlung> map) {
        Map.Entry<Integer, Zahlung> entryWithMaxKey = null;

        for (Map.Entry<Integer, Zahlung> currentEntry : map.entrySet()) {
            if ( entryWithMaxKey == null || currentEntry.getKey().compareTo(entryWithMaxKey.getKey()) > 0) {
                entryWithMaxKey = currentEntry;
            }
        }
        return Integer.valueOf(String.valueOf(entryWithMaxKey.getValue().getId()));
    }

    public double calculateBudget(Projekt p){
        double budget = p.getBudget();

        for (Zahlung z: p.getGeordneteZahlungen().values()) {
            budget = budget + z.getBetrag();
        }
        return budget;
    }

    //TODO Custom Alert?
    public Alert paymentDetails(Zahlung z){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Zahlungsdetails");
        alert.setHeaderText("ID: " + z.getId() + " \n"
                + "Betrag: " + z.getBetrag() + "\n"
                + "Am: " + z.getDatum());

        alert.setContentText("Kategorie: "+z.getKategorieName() + "\n"
                             + "Grund: "+z.getGrund());
        return alert;
    }

    public Alert errorAlert(String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

}
