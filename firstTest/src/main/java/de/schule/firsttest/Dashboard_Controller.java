package de.schule.firsttest;

import de.schule.firsttest.objs.BetragsEnum;
import de.schule.firsttest.objs.Kategorie;
import de.schule.firsttest.objs.Projekt;
import de.schule.firsttest.objs.Zahlung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class Dashboard_Controller {

    List<Projekt> alleProjekte;
    @FXML
    private Button btnDetails;

    @FXML
    private TableView<Zahlung> tblZahlung = new TableView<>();


    @FXML
    private TableColumn<Zahlung, Integer> clmnID;


    @FXML
    private TableColumn<Zahlung, Double> clmnBetrag;

    @FXML
    private TableColumn<Zahlung, Date> clmnDatum;

    @FXML
    private TableColumn<Zahlung, String> clmnGrund;

    @FXML
    private TableColumn<Zahlung, String> clmnKategorie;

    @FXML
    private TableColumn<Zahlung, BetragsEnum> clmnOperator;

    @FXML
    private Label lblBudget;

    @FXML
    private ListView<String> projektListe = new ListView<>();

    @FXML
    void clickedItem(MouseEvent event) {
        tblZahlung.getItems().clear();
        //Ändern um mit MODEL Class zu funktionieren
        String projektName = projektListe.getSelectionModel().getSelectedItem();

        for (Projekt p: alleProjekte) {
            System.out.println(p.getName());
            if(p.getName().equals(projektName)){

                for (int z = 0; z <= getMaxKey(p.getGeordneteZahlungen()); z++) {
                    tblZahlung.getItems().add(p.bekommeZahlung(z));
                }
                break;
            }
        }
    }


    @FXML
    void openDetails(ActionEvent event) {

    }

    @FXML
    public void initialize(){
        //Spalten Attribute
        clmnBetrag.setCellValueFactory(new PropertyValueFactory<>("betrag"));
        clmnDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        clmnGrund.setCellValueFactory(new PropertyValueFactory<>("grund"));
        clmnKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorieName"));
        clmnOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));
        clmnID.setCellValueFactory(new PropertyValueFactory<>("id"));

        //TODO: Entferne Test Variabeln
        //Erstelle ersteZahlung
        Zahlung ersteZahlung = new Zahlung(BetragsEnum.PLUS,100.00,new Kategorie(UUID.randomUUID(),"Sprit"),"Warum net?",new Date(2004,02,23));
        Zahlung zweiteZahlung = new Zahlung(BetragsEnum.MINUS,300,new Kategorie(UUID.randomUUID(),"Essen"),"Hmmm geil",new Date(20120112));
        Zahlung dritteZahlung = new Zahlung(BetragsEnum.MINUS,5,new Kategorie(UUID.randomUUID(),"Kaugumi"),"Worth it",new Date(20041202));


        //Erstelle Projekt + eintrag Hinzufügen
        Projekt p1 = new Projekt("Test Projekt");
        p1.eintragHinzufügen(0,ersteZahlung);

        Projekt p2 = new Projekt("Zweit Projekt");
        p2.eintragHinzufügen(0,ersteZahlung);
        p2.eintragHinzufügen(1,zweiteZahlung);
        p2.eintragHinzufügen(3,dritteZahlung);
        p2.eintragHinzufügen(10,ersteZahlung);

        //Alle Projekte zsmfügen
        alleProjekte = new ArrayList<>();
        alleProjekte.add(p1);
        alleProjekte.add(p2);

        //Projekte in Liste fügen
        for (Projekt p : alleProjekte) {
            projektListe.getItems().add(p.getName());
        }
    }

    public Integer getMaxKey(Map<Integer, Zahlung> map)
    {
        Map.Entry<Integer, Zahlung> entryWithMaxKey = null;

        for (Map.Entry<Integer, Zahlung> currentEntry : map.entrySet()) {
            if ( entryWithMaxKey == null || currentEntry.getKey().compareTo(entryWithMaxKey.getKey()) > 0) {
                entryWithMaxKey = currentEntry;
            }
        }
        return Integer.valueOf(String.valueOf(entryWithMaxKey.getValue().getId()));
    }
}
