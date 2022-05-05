module de.schule.firsttest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;

    opens de.schule.firsttest to javafx.fxml;
    opens de.schule.firsttest.objs to javafx.fxml, com.google.gson;
    exports de.schule.firsttest;
    exports de.schule.firsttest.objs;
    exports de.schule.firsttest.controllers;
    opens de.schule.firsttest.controllers to javafx.fxml;
}
