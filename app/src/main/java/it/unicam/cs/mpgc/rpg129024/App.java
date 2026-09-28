package it.unicam.cs.mpgc.rpg129024;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("RPG");
        stage.setScene(new Scene(new StackPane(new Label("Setup completato!")), 400, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}