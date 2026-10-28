package it.unicam.cs.mpgc.rpg129024;

import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaHome;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Avvio dell'applicazione JavaFX.
 *
 * @author Davide Caliri
 */
public class LumenApp extends Application {

    public static final int LARGHEZZA = 960;
    public static final int ALTEZZA = 600;

    @Override
    public void start(Stage stage) {
        Risorse.caricaFont();
        Scene scene = new Scene(new PaginaHome().getRadice(), LARGHEZZA, ALTEZZA);
        scene.getStylesheets().add(Risorse.foglioDiStile());
        
        stage.setTitle("Lumen");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}