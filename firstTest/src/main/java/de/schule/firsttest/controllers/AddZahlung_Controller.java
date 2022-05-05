package de.schule.firsttest.controllers;

import de.schule.firsttest.FMS_App;
import de.schule.firsttest.objs.Kategorie;
import de.schule.firsttest.objs.Projekt;
import de.schule.firsttest.objs.Zahlung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddZahlung_Controller {

    @FXML
    private ChoiceBox<String> chbProjekt;

    @FXML
    private DatePicker payDate;

    @FXML
    private TextField txtBetrag;

    @FXML
    private TextField txtGrund;

    @FXML
    private TextField txtKategorie;

    @FXML
    void addZahlung(ActionEvent event) {
        try{

            String projektName = chbProjekt.getValue();
            double betrag = Double.parseDouble(txtBetrag.getText());
            String grund = txtGrund.getText();
            LocalDate datum = payDate.getValue();
            String kategorieName = txtKategorie.getText();
            Kategorie kategorie = new Kategorie(kategorieName);
            Zahlung z = new Zahlung(betrag,kategorie,grund,String.valueOf(datum));

            FMS_App.getModel().addZahlungToProjekt(projektName,z); // Richtige ID verwenden
            FMS_App.getModel().updateListeners();

            Stage stage = (Stage) chbProjekt.getScene().getWindow();
            stage.close();

        }catch (Exception e){
            Alert error = FMS_App.getModel().errorAlert("Etwas lief schief :/","Bitte überprüfen Sie ihre Eingabe.");
            error.showAndWait();
        }
    }
    @FXML
    public void initialize(){
        for (Projekt p:FMS_App.getModel().getProjekte()) {
            chbProjekt.getItems().add(p.getName());
        }
    }
}
