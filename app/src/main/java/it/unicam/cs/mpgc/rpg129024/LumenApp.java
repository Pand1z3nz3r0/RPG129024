package it.unicam.cs.mpgc.rpg129024;

import it.unicam.cs.mpgc.rpg129024.persistence.JsonDiarioRepository;
import it.unicam.cs.mpgc.rpg129024.view.Navigatore;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Avvio dell'applicazione JavaFX.
 * <p>
 * Crea la finestra e l'unica scena del gioco, poi affida al
 * {@link Navigatore} il passaggio tra le schermate, a partire dalla home.
 *
 * @author Davide Caliri
 */
public class LumenApp extends Application {

    public static final int LARGHEZZA = 960;
    public static final int ALTEZZA = 600;

    @Override
    public void start(Stage stage) {
        Risorse.caricaFont();

        // la radice iniziale è vuota: il navigatore la sostituisce subito con la home
        Scene scena = new Scene(new StackPane(), LARGHEZZA, ALTEZZA);
        scena.getStylesheets().add(Risorse.foglioDiStile());

        Navigatore navigatore = new Navigatore(scena, new JsonDiarioRepository());
        navigatore.vaiAllaPaginaHome();

        stage.setTitle("Lumen");
        stage.setScene(scena);
        stage.setResizable(false);
        stage.show();
    }
}