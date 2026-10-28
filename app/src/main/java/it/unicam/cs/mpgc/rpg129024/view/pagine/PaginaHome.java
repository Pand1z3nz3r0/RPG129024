package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.view.Pagina;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Schermata iniziale: titolo, scelta del nome e avvio di una nuova partita.
 * <p>
 * Il bottone per iniziare la partita è attivo solo quando il nome rispetta
 * la lunghezza prevista per un {@link Avventuriero}; in caso contrario viene
 * mostrato un messaggio di errore. Dalla schermata si accede anche alla guida
 * e al diario delle avventure.
 *
 * @author Davide Caliri
 */
public class PaginaHome implements Pagina {

    private static final String SUGGERIMENTO = "DA " + Avventuriero.LUNGHEZZA_MIN_NOME
            + " A " + Avventuriero.LUNGHEZZA_MAX_NOME + " CARATTERI";

    private final StackPane radice;

    /**
     * Costruisce la schermata iniziale: bottoni per guida e diario, intestazione,
     * campo del nome con controllo della lunghezza, bottone per iniziare la
     * partita e nota sulla versione demo.
     *
     * @throws NullPointerException se un'immagine non è presente nelle risorse
     */
    public PaginaHome() {
        // in alto a destra
        Button guida = new Button("GUIDA");
        Button diario = new Button("DIARIO");
        guida.getStyleClass().add("bottone-secondario");
        diario.getStyleClass().add("bottone-secondario");
        Region spazio = new Region();
        HBox.setHgrow(spazio, Priority.ALWAYS);
        HBox barraAlta = new HBox(10, spazio, guida, diario);

        // al centro
        ImageView intestazione = new ImageView(Risorse.immagine("/img/intestazione-1.png"));

        Label sottotitolo = new Label("NEL BUIO DI OMBRAFONDA, L'UNICA LUCE È LA TUA");
        sottotitolo.getStyleClass().add("sottotitolo");

        TextField campoNome = new TextField();
        campoNome.setPromptText("DAI UN NOME AL TUO AVVENTURIERO!");
        campoNome.setMaxWidth(560);
        campoNome.getStyleClass().add("campo");

        Label messaggio = new Label(SUGGERIMENTO);
        messaggio.getStyleClass().add("suggerimento");

        Button inizia = new Button("INIZIA UNA NUOVA PARTITA");
        inizia.getStyleClass().add("bottone-pixel");
        inizia.setDisable(true);

        campoNome.textProperty().addListener((osservato, vecchio, nuovo) -> {
            boolean valido = Avventuriero.isNomeValido(nuovo);
            inizia.setDisable(!valido);
            boolean mostraErrore = !valido && !nuovo.isEmpty();
            messaggio.setText(mostraErrore
                    ? "IL NOME DEVE AVERE " + SUGGERIMENTO
                    : SUGGERIMENTO);
            messaggio.getStyleClass().setAll(mostraErrore ? "errore" : "suggerimento");
        });

        // TODO con il navigatore:
        // inizia.setOnAction(e -> navigatore.vaiAllaScelta(campoNome.getText().trim()));
        // guida.setOnAction(e -> navigatore.vaiAllaGuida());
        // diario.setOnAction(e -> navigatore.vaiAlDiario());

        VBox centro = new VBox(14, intestazione, sottotitolo, campoNome, messaggio, inizia);
        centro.setAlignment(Pos.TOP_CENTER);

        // in basso
        Label nota = new Label("VERSIONE DEMO - LA PRIMA CAVERNA DI OMBRAFONDA");
        nota.getStyleClass().add("nota");
        BorderPane.setAlignment(nota, Pos.CENTER);

        BorderPane contenuto = new BorderPane(centro);
        contenuto.setTop(barraAlta);
        contenuto.setBottom(nota);
        contenuto.setPadding(new Insets(18));

        radice = new StackPane(new ImageView(Risorse.immagine("/img/sfondo.png")), contenuto);
    }

    @Override
    public Parent getRadice() {
        return radice;
    }

}