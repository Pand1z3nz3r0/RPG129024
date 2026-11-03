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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * Schermata mostrata quando l'avventuriero cade in battaglia.
 * <p>
 * La partita è già stata registrata nel diario dal {@link Navigatore}. Il
 * giocatore può riprovare con lo stesso nome e tipo, ripartendo dall'inizio
 * della caverna, oppure tornare alla home.
 *
 * @author Davide Caliri
 */
public class PaginaSconfitta implements Pagina {

    private final StackPane radice;

    /**
     * Costruisce la schermata di sconfitta.
     *
     * @param navigatore il navigatore, per passare alle altre schermate
     * @param partita    la partita appena persa
     */
    public PaginaSconfitta(Navigatore navigatore, Partita partita) {
        Avventuriero caduto = partita.getAvventuriero();

        Label titolo = new Label("SEI CADUTO");
        titolo.getStyleClass().setAll("titolo-pagina", "titolo-sconfitta");

        ImageView ritratto = new ImageView(Risorse.ritratto(caduto.getTipo(), 144));
        // sprite in bianco e nero e più scuro
        ritratto.setEffect(new ColorAdjust(0, -1, -0.3, 0));

        Label testo = new Label("Il buio di Ombrafonda ha avuto la meglio su " + caduto.getNome() + ".\n"
                + "Nemici sconfitti: " + partita.getNemiciSconfitti() + ".\n"
                + "La tua avventura è stata scritta nel diario.");
        testo.getStyleClass().add("testo-storia");
        testo.setTextAlignment(TextAlignment.CENTER);

        VBox riquadro = new VBox(testo);
        riquadro.getStyleClass().add("riquadro");
        riquadro.setAlignment(Pos.CENTER);
        riquadro.setMaxWidth(720);

        Button riprova = new Button("RIPROVA");
        riprova.getStyleClass().add("bottone-pixel");
        riprova.setOnAction(e -> {
            // nuovo avventuriero con lo stesso nome e tipo, e una partita da capo
            Avventuriero nuovo = caduto.getTipo().creaAvventuriero(caduto.getNome());
            navigatore.vaiAllaPaginaMappa(new Partita(nuovo));
        });

        Button home = new Button("HOME");
        home.getStyleClass().add("bottone-secondario");
        home.setOnAction(e -> navigatore.vaiAllaPaginaHome());

        HBox bottoni = new HBox(16, riprova, home);
        bottoni.setAlignment(Pos.CENTER);

        VBox contenuto = new VBox(20, titolo, ritratto, riquadro, bottoni);
        contenuto.setAlignment(Pos.CENTER);

        radice = new StackPane(new ImageView(Risorse.immagine("/img/sfondo.png")), contenuto);
    }

    @Override
    public Parent getRadice() {
        return radice;
    }
}