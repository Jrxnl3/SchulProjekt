module de.schule.firsttest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens de.schule.firsttest to javafx.fxml;
    opens de.schule.firsttest.objs to javafx.fxml;
    exports de.schule.firsttest;
    exports de.schule.firsttest.objs;
    exports de.schule.firsttest.controllers;
    opens de.schule.firsttest.controllers to javafx.fxml;
}
