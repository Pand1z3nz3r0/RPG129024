package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.model.gioco.Partita;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.view.Navigatore;
import it.unicam.cs.mpgc.rpg129024.view.Pagina;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.Locale;

/**
 * Schermata che racconta la storia prima dell'inizio della partita.
 * <p>
 * Il testo è letto da {@code /testi/storia.txt}, dove i segnaposto
 * {@code {nome}} e {@code {tipo}} vengono sostituiti con il nome e il tipo
 * dell'avventuriero scelto.
 *
 * @author Davide Caliri
 */
public class PaginaStoria implements Pagina {

    private static final double LARGHEZZA_TESTO = 720;

    private final StackPane radice;

    /**
     * Costruisce la schermata della storia.
     *
     * @param navigatore   il navigatore, per iniziare la partita
     * @param avventuriero l'avventuriero appena creato
     * @throws IllegalStateException se il file della storia non è presente nelle risorse
     */
    public PaginaStoria(Navigatore navigatore, Avventuriero avventuriero) {
        Label titolo = new Label("LA STORIA");
        titolo.getStyleClass().setAll("titolo-pagina", "titolo-medio");

        ImageView ritratto = new ImageView(Risorse.ritratto(avventuriero.getTipo(), 96));

        String storia = Risorse.testo("/testi/storia.txt")
                .replace("{nome}", avventuriero.getNome())
                .replace("{tipo}", avventuriero.getTipo().getNome().toLowerCase(Locale.ROOT));
        Label testo = new Label(storia);
        testo.getStyleClass().add("testo-storia");
        testo.setWrapText(true);
        testo.setTextAlignment(TextAlignment.CENTER);

        VBox riquadro = new VBox(16, ritratto, testo);
        riquadro.getStyleClass().add("riquadro");
        riquadro.setAlignment(Pos.CENTER);
        riquadro.setMaxWidth(LARGHEZZA_TESTO);

        Button continua = new Button("ENTRA NELLA CAVERNA");
        continua.getStyleClass().add("bottone-pixel");
        continua.setOnAction(e -> navigatore.vaiAllaPaginaMappa(new Partita(avventuriero)));

        VBox contenuto = new VBox(20, titolo, riquadro, continua);
        contenuto.setAlignment(Pos.CENTER);

        radice = new StackPane(new ImageView(Risorse.immagine("/img/sfondo.png")), contenuto);
    }

    @Override
    public Parent getRadice() {
        return radice;
    }
}