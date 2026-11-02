package it.unicam.cs.mpgc.rpg129024.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Finestre di dialogo del gioco, con lo stesso stile pixel delle pagine.
 * <p>
 * Le finestre sono modali: il gioco resta in attesa finché il giocatore
 * non le chiude.
 *
 * @author Davide Caliri
 */
public final class Dialoghi {

    private static final double LARGHEZZA = 480;

    private Dialoghi() {
        // classe di sole funzioni statiche: non si istanzia
    }

    /**
     * Mostra un messaggio con un solo bottone per chiuderlo.
     *
     * @param titolo il titolo della finestra
     * @param testo  il messaggio
     */
    public static void messaggio(String titolo, String testo) {
        Alert alert = crea(Alert.AlertType.INFORMATION, titolo, testo, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Chiede una conferma al giocatore.
     *
     * @param titolo  il titolo della finestra
     * @param domanda la domanda da porre
     * @return {@code true} se il giocatore ha risposto di sì
     */
    public static boolean conferma(String titolo, String domanda) {
        ButtonType si = new ButtonType("SÌ", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.NO);
        Alert alert = crea(Alert.AlertType.CONFIRMATION, titolo, domanda, si, no);
        return alert.showAndWait().filter(si::equals).isPresent();
    }

    private static Alert crea(Alert.AlertType tipo, String titolo, String testo, ButtonType... bottoni) {
        Alert alert = new Alert(tipo, testo, bottoni);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.getDialogPane().setPrefWidth(LARGHEZZA);
        alert.getDialogPane().getStylesheets().add(Risorse.foglioDiStile());
        return alert;
    }
}
