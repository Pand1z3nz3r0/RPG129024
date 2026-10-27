package it.unicam.cs.mpgc.rpg129024;

import javafx.application.Application;

/**
 * Punto di avvio del programma.
 * <p>
 * Non estende {@link Application}: in questo modo l'applicazione si avvia
 * anche senza configurare JavaFX come modulo.
 *
 * @author Davide Caliri
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(LumenApp.class, args);
    }
}