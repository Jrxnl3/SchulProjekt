package de.schule.firsttest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FMS_App extends Application {

    private static Model model;

    @Override
    public void start(Stage stage) throws IOException {
        model = new Model();

        FXMLLoader fxmlLoader = new FXMLLoader(FMS_App.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,600);
        stage.setTitle("Finanz Management Software");
        stage.setScene(scene);
        stage.show();
    }

    public static Model getModel() {
        return model;
    }

    public static void main(String[] args) {
        launch();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> model.saveProject()));
    }
}