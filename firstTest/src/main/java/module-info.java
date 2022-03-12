module de.schule.firsttest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens de.schule.firsttest to javafx.fxml;
    exports de.schule.firsttest;
}