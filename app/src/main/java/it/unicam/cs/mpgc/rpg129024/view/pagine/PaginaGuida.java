package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.view.Navigatore;
import it.unicam.cs.mpgc.rpg129024.view.Pagina;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.UncheckedIOException;

/**
 * Schermata della guida di gioco.
 * <p>
 * Mostra le regole di Lumen (obiettivo, movimento, battaglia, crescita
 * dell'avventuriero, porta e fine della partita) in un riquadro scorrevole,
 * con un bottone per tornare alla schermata iniziale.
 * <p>
 * Il testo della guida è letto dal file {@code /testi/guida.txt} nelle
 * risorse, così può essere modificato senza cambiare il codice.
 *
 * @author Davide Caliri
 */
public class PaginaGuida implements Pagina {

    /**
     * Contenitore principale della pagina: sfondo e contenuto sovrapposti.
     */
    private final StackPane radice;

    /**
     * Costruisce la pagina della guida: titolo, testo scorrevole e bottone
     * per tornare alla home.
     *
     * @param navigatore il navigatore, per tornare alla home
     * @throws IllegalStateException se il file della guida non è presente nelle risorse
     * @throws UncheckedIOException  se il file della guida non può essere letto
     */
    public PaginaGuida(Navigatore navigatore) {
        Button home = new Button("HOME");
        home.getStyleClass().addAll("bottone-pixel", "bottone-grande");
        home.setOnAction(e -> navigatore.vaiAllaPaginaHome());

        Label titolo = new Label("GUIDA");
        titolo.getStyleClass().setAll("titolo-pagina");

        Label testoGuida = new Label(Risorse.testo("/testi/guida.txt"));
        testoGuida.getStyleClass().add("sottotitolo");
        testoGuida.setTextAlignment(TextAlignment.CENTER);
        testoGuida.setWrapText(true);

        ScrollPane scroll = new ScrollPane(testoGuida);
        scroll.setMaxWidth(720);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.getStyleClass().add("scroll-guida");

        VBox vbox = new VBox(14, titolo, scroll, home);
        vbox.getStyleClass().add("vbox-guida");
        vbox.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(scroll, Priority.ALWAYS);
        vbox.setPadding(new Insets(0, 0, 30, 0));

        radice = new StackPane(new ImageView(Risorse.immagine("/img/sfondo.png")), vbox);
    }

    @Override
    public Parent getRadice() {
        return radice;
    }
}
