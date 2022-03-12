package de.schule.firsttest;

import de.schule.firsttest.objs.BetragsEnum;
import de.schule.firsttest.objs.Zahlung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.Date;

public class Dashboard_Controller {

    @FXML
    private Button btnDetails;

    @FXML
    private TableView<Zahlung> tblZahlung = new TableView<>();

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

    }
    @FXML
    public void initialize(){

        clmnBetrag.setCellValueFactory(new PropertyValueFactory<>("betrag"));
        clmnDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        clmnGrund.setCellValueFactory(new PropertyValueFactory<>("grund"));
        clmnKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
        clmnOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));

        ObservableList<Zahlung> zahlungen = FXCollections.observableArrayList();
        Zahlung ersteZahlung = new Zahlung(BetragsEnum.PLUS,100.00,null,"Warum net?",new Date(2004,02,23));
        System.out.println(ersteZahlung);
        zahlungen.add(ersteZahlung);


        tblZahlung.setItems(zahlungen);
    }
}
