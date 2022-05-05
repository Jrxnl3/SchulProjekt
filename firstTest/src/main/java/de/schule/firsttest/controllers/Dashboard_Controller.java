package de.schule.firsttest.controllers;

import de.schule.firsttest.FMS_App;
import de.schule.firsttest.listeners.TableViewUpdate;
import de.schule.firsttest.objs.Projekt;
import de.schule.firsttest.objs.Zahlung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.*;

public class Dashboard_Controller implements TableViewUpdate {

    @FXML
    private Button btnDetails;

    @FXML
    private TableView<Zahlung> tblZahlung = new TableView<>();


    @FXML
    private TableColumn<Zahlung, Integer> clmnID;


    @FXML
    private TableColumn<Zahlung, Double> clmnBetrag;

    @FXML
    private TableColumn<Zahlung, LocalDate> clmnDatum;

    @FXML
    private TableColumn<Zahlung, String> clmnGrund;

    @FXML
    private TableColumn<Zahlung, String> clmnKategorie;

    @FXML
    private Label lblBudget;

    @FXML
    private ListView<String> projektListe = new ListView<>();

    @FXML
    void addZahlung(ActionEvent event) {
        Stage stage = FMS_App.getModel().createStageZahlung();
        stage.show();
    }

    @FXML
    void deleteItem(ActionEvent event) {
        String projektName = projektListe.getSelectionModel().getSelectedItem();

        Zahlung z = tblZahlung.getSelectionModel().getSelectedItem();
        FMS_App.getModel().removeZahlungFromProjekt(projektName,z);

        FMS_App.getModel().updateListeners();

    }

    @FXML
    void clickedItem(MouseEvent event) {
        if(projektListe.getSelectionModel().getSelectedItem() == null)
            return;

        tblZahlung.getItems().clear();
        lblBudget.setText("");

        String projektName = projektListe.getSelectionModel().getSelectedItem();

        Projekt clickedProjekt = FMS_App.getModel().findProjekt(projektName);

        LinkedList<Zahlung> zahlungen = FMS_App.getModel().sortiereZahlung(clickedProjekt.getGeordneteZahlungen());
        tblZahlung.getItems().addAll(zahlungen);


        //TODO: Budget in Extra Window öffnen
        lblBudget.setText(String.valueOf(FMS_App.getModel().calculateBudget(clickedProjekt)));
    }

    @FXML
    void openDetails(ActionEvent event) {
        try {
            Zahlung z = tblZahlung.getSelectionModel().getSelectedItem();
            Alert alert = FMS_App.getModel().paymentDetails(z);
            alert.showAndWait();
        }catch (Exception e){
            Alert error = FMS_App.getModel().errorAlert("Tabellen Error","Du musst vorher eine Zahlung auswählen ");
            error.showAndWait();
        }
    }

    @FXML
    public void initialize(){
        clmnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmnBetrag.setCellValueFactory(new PropertyValueFactory<>("betrag"));
        clmnKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorieName"));
        clmnDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        clmnGrund.setCellValueFactory(new PropertyValueFactory<>("grund"));

        for (Projekt p : FMS_App.getModel().getProjekte()) {
            projektListe.getItems().add(p.getName());
        }
    }

    @Override
    public void updateTable() {
        tblZahlung.refresh();
    }
}
