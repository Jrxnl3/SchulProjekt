package de.schule.firsttest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.schule.firsttest.objs.Kategorie;
import de.schule.firsttest.objs.Projekt;
import de.schule.firsttest.objs.Zahlung;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class Model {
    private ArrayList<Projekt> alleProjekte;

    final String excDir = System.getProperty("user.dir");
    final String cfgDir = excDir + "/configs";

    final String cfgFile = cfgDir + "/cfg.json";
    final String projectFile = cfgDir + "/projects.json";

    public Model() {
        alleProjekte = new ArrayList<>();


        File cfgFolder = new File(cfgDir);
        if (!cfgFolder.exists())
            cfgFolder.mkdir();

        createFile(cfgFile);
        createFile(projectFile);

        loadProject();
    }

    public void saveProject() {
        try {
            FileWriter file = new FileWriter(projectFile);
            file.write(new Gson().toJson(alleProjekte));
            file.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void loadProject(){
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Path.of(projectFile));
            alleProjekte = gson.fromJson(reader,new TypeToken<List<Projekt>>() {}.getType());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO Highest ID von Projekt rausfinden

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

    public void addZahlungToProjekt(String projektName, Zahlung z){
        Projekt p = findProjekt(projektName);
        p.eintragHinzufügen(p.getGeordneteZahlungen().size(),z); //TODO: Fehler bei TestProjekt zwei wegen IDs? + 1 Spacer
    }

    public Stage createStageZahlung(){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FMS_App.class.getResource("addZahlung.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 300,400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Add Zahlung");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}
