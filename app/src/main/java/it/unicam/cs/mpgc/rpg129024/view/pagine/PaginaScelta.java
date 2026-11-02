package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.TipoAvventuriero;
import it.unicam.cs.mpgc.rpg129024.view.Navigatore;
import it.unicam.cs.mpgc.rpg129024.view.Pagina;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.util.Locale;

/**
 * Schermata in cui il giocatore sceglie il tipo del proprio avventuriero.
 * <p>
 * Mostra una carta per ogni costante di {@link TipoAvventuriero}, con
 * sprite, descrizione, statistiche e nome dell'abilità: aggiungendo un nuovo
 * tipo al catalogo, la sua carta compare da sola. I tipi non ancora
 * disponibili sono mostrati in ombra e non possono essere scelti.
 *
 * @author Davide Caliri
 */
public class PaginaScelta implements Pagina {

    private static final double LARGHEZZA_CARTA = 260;
    private static final double LATO_RITRATTO = 96;

    private final Navigatore navigatore;
    private final String nome;
    private final StackPane radice;

    /**
     * Costruisce la schermata di scelta.
     *
     * @param navigatore il navigatore, per passare alle altre schermate
     * @param nome       il nome scelto dal giocatore nella home
     */
    public PaginaScelta(Navigatore navigatore, String nome) {
        this.navigatore = navigatore;
        this.nome = nome;

        Label titolo = new Label("SCEGLI IL TUO AVVENTURIERO");
        titolo.getStyleClass().setAll("titolo-pagina", "titolo-medio");

        Label domanda = new Label("CHI SARAI, " + nome.toUpperCase(Locale.ROOT) + "?");
        domanda.getStyleClass().add("sottotitolo");

        HBox carte = new HBox(24);
        carte.setAlignment(Pos.CENTER);
        for (TipoAvventuriero tipo : TipoAvventuriero.values()) {
            carte.getChildren().add(creaCarta(tipo));
        }

        Button indietro = new Button("INDIETRO");
        indietro.getStyleClass().add("bottone-secondario");
        indietro.setOnAction(e -> navigatore.vaiAllaPaginaHome());

        VBox contenuto = new VBox(12, titolo, domanda, carte, indietro);
        contenuto.setAlignment(Pos.TOP_CENTER);
        contenuto.setPadding(new Insets(0, 0, 20, 0));

        radice = new StackPane(new ImageView(Risorse.immagine("/img/sfondo.png")), contenuto);
    }

    @Override
    public Parent getRadice() {
        return radice;
    }

    /**
     * Crea la carta di un tipo di avventuriero.
     */
    private VBox creaCarta(TipoAvventuriero tipo) {
        ImageView ritratto = new ImageView(Risorse.ritratto(tipo, LATO_RITRATTO));

        Label nomeTipo = new Label(tipo.getNome().toUpperCase(Locale.ROOT));
        nomeTipo.getStyleClass().add("nome-card");

        Label descrizione = new Label(tipo.getDescrizione());
        descrizione.getStyleClass().add("descrizione-card");
        descrizione.setWrapText(true);
        descrizione.setTextAlignment(TextAlignment.CENTER);

        Label statistiche = new Label(tipo.isDisponibile()
                ? "HP " + tipo.getHp() + "   AD " + tipo.getAd()
                : "HP ???   AD ???");
        statistiche.getStyleClass().add("statistiche");

        Label abilita = new Label(tipo.isDisponibile()
                ? tipo.getAbilita().getNome().toUpperCase(Locale.ROOT)
                : "???");
        abilita.getStyleClass().add("nome-abilita");

        // le etichette non si accorciano mai: se manca spazio si riduce solo lo spazio elastico
        for (Label etichetta : new Label[]{nomeTipo, descrizione, statistiche, abilita}) {
            etichetta.setMinHeight(Region.USE_PREF_SIZE);
        }

        // spazio elastico: spinge il bottone in fondo alla carta
        Region spazio = new Region();
        VBox.setVgrow(spazio, Priority.ALWAYS);

        Button scegli = new Button(tipo.isDisponibile() ? "SCEGLI" : "IN ARRIVO");
        scegli.getStyleClass().add("bottone-pixel");
        scegli.setDisable(!tipo.isDisponibile());
        scegli.setOnAction(e -> navigatore.vaiAllaPaginaStoria(tipo.creaAvventuriero(nome)));

        VBox carta = new VBox(10, ritratto, nomeTipo, descrizione, statistiche, abilita, spazio, scegli);
        carta.getStyleClass().add("card");
        carta.setAlignment(Pos.TOP_CENTER);
        carta.setPrefWidth(LARGHEZZA_CARTA);

        if (!tipo.isDisponibile()) {
            // il tipo non ancora disponibile appare come una sagoma scura
            ritratto.setEffect(new ColorAdjust(0, -1, -0.8, 0));
            carta.getStyleClass().add("card-bloccata");
        }
        return carta;
    }
}