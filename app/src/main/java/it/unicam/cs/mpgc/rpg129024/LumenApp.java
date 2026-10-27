package it.unicam.cs.mpgc.rpg129024;

import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaHome;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

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
        caricaFontPixel();

        Scene scene = new Scene(new PaginaHome().getRadice(), LARGHEZZA, ALTEZZA);
        scene.getStylesheets().add(getClass().getResource("/css/lumen.css").toExternalForm());

        stage.setTitle("Lumen");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void caricaFontPixel() {
        InputStream font = getClass().getResourceAsStream("/font/PressStart2P-Regular.ttf");
        if (font != null) {
            Font.loadFont(font, 12);
        }
    }
}