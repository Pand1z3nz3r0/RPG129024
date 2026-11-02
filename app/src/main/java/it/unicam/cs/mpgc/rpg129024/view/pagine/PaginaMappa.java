package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.model.gioco.*;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg129024.view.Dialoghi;
import it.unicam.cs.mpgc.rpg129024.view.Navigatore;
import it.unicam.cs.mpgc.rpg129024.view.Pagina;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Schermata della caverna, in cui l'avventuriero si muove con la tastiera.
 * <p>
 * La mappa è disegnata su un {@link Canvas}, una casella alla volta. La
 * caverna è buia: la lanterna illumina solo le caselle vicine
 * all'avventuriero, quindi i mostri si vedono solo avvicinandosi. La porta
 * resta sempre appena visibile, per indicare la meta.
 * <p>
 * La pagina si limita a tradurre i tasti in una {@link Direzione} e a
 * reagire all'{@link EsitoMovimento} restituito dalla partita: tutte le
 * regole del movimento sono nel modello.
 *
 * @author Davide Caliri
 */
public class PaginaMappa implements Pagina {

    /**
     * Lato di una casella in pixel: 48 = 16 × 3, così gli sprite restano nitidi.
     */
    private static final int CELLA = 48;
    private static final int LARGHEZZA = Mappa.COLONNE * CELLA;
    private static final int ALTEZZA = Mappa.RIGHE * CELLA;

    /**
     * Colore delle caselle illuminate dalla lanterna e di quelle al buio.
     */
    private static final Color LUCE = Color.rgb(255, 214, 120, 0.12);
    private static final Color BUIO = Color.rgb(0, 0, 0, 0.96);

    private final Navigatore navigatore;
    private final Partita partita;
    private final Mappa mappa;
    private final BorderPane radice;
    private final Canvas canvas = new Canvas(LARGHEZZA, ALTEZZA);

    private final Image pavimento = Risorse.immagine("/img/caverna.png", LARGHEZZA, ALTEZZA);
    private final Image eroe;
    /**
     * Sprite dei mostri già caricati, per nome: così ogni immagine si carica una volta sola.
     */
    private final Map<String, Image> spriteMostri = new HashMap<>();

    /**
     * Costruisce la schermata della mappa.
     *
     * @param navigatore il navigatore, per passare alle altre schermate
     * @param partita    la partita in corso
     */
    public PaginaMappa(Navigatore navigatore, Partita partita) {
        this.navigatore = navigatore;
        this.partita = partita;
        this.mappa = partita.getMappa();
        this.eroe = Risorse.ritratto(partita.getAvventuriero().getTipo(), CELLA);

        StackPane cornice = new StackPane(canvas);
        cornice.getStyleClass().add("cornice-mappa");
        cornice.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Label aiuto = new Label("W A S D / FRECCE PER MUOVERTI");
        aiuto.getStyleClass().add("nota");
        BorderPane.setAlignment(aiuto, Pos.CENTER);

        radice = new BorderPane(cornice);
        radice.setTop(creaBarraInfo());
        radice.setBottom(aiuto);
        radice.setPadding(new Insets(10));
        radice.getStyleClass().add("pagina-mappa");

        // il filtro riceve i tasti prima dei figli, così le frecce non spostano il focus
        radice.addEventFilter(KeyEvent.KEY_PRESSED, this::tastoPremuto);

        disegna();
    }

    @Override
    public Parent getRadice() {
        return radice;
    }

    /**
     * La pagina prende il focus, altrimenti non riceverebbe i tasti premuti.
     */
    @Override
    public void mostrata() {
        radice.requestFocus();
    }

    /**
     * Crea la barra in alto con le statistiche dell'avventuriero e il bottone HOME.
     */
    private HBox creaBarraInfo() {
        Avventuriero avventuriero = partita.getAvventuriero();

        Label nome = new Label(avventuriero.getNome().toUpperCase(Locale.ROOT)
                + " - " + avventuriero.getTipo().getNome().toUpperCase(Locale.ROOT));
        nome.getStyleClass().add("nome-voce");

        Label statistiche = new Label("HP " + avventuriero.getHp() + "/" + avventuriero.getHpMax()
                + "   AD " + avventuriero.getAd());
        statistiche.getStyleClass().add("info");

        Label mostri = new Label("MOSTRI RIMASTI: " + mappa.getMostriRimasti());
        mostri.getStyleClass().add("info");

        Region spazio = new Region();
        HBox.setHgrow(spazio, Priority.ALWAYS);

        Button home = new Button("HOME");
        home.getStyleClass().add("bottone-secondario");
        // senza focus il bottone non "ruba" le frecce alla mappa
        home.setFocusTraversable(false);
        home.setOnAction(e -> abbandona());

        HBox barra = new HBox(24, nome, statistiche, spazio, mostri, home);
        barra.setAlignment(Pos.CENTER_LEFT);
        barra.getStyleClass().add("barra-info");
        BorderPane.setMargin(barra, new Insets(0, 0, 10, 0));
        return barra;
    }

    /**
     * Traduce il tasto premuto in una direzione e prova a muovere l'avventuriero.
     */
    private void tastoPremuto(KeyEvent evento) {
        Direzione direzione = switch (evento.getCode()) {
            case W, UP -> Direzione.SU;
            case S, DOWN -> Direzione.GIU;
            case A, LEFT -> Direzione.SINISTRA;
            case D, RIGHT -> Direzione.DESTRA;
            default -> null;
        };
        if (direzione != null) {
            evento.consume();
            muovi(direzione);
        }
    }

    /**
     * Muove l'avventuriero e reagisce a ciò che è successo.
     */
    private void muovi(Direzione direzione) {
        if (partita.isFinita() || partita.isInBattaglia()) {
            return;
        }
        EsitoMovimento esito = partita.muovi(direzione);
        switch (esito) {
            case MOSSO, BLOCCATO -> disegna();
            case PORTA_CHIUSA -> Dialoghi.messaggio("PORTA CHIUSA",
                    "Una magia oscura sigilla la porta.\n"
                            + "Sconfiggi tutti i mostri per aprirla: ne restano "
                            + mappa.getMostriRimasti() + ".");
            case INCONTRO_MOSTRO -> navigatore.vaiAllaPaginaBattaglia(partita);
            case PORTA -> {
                disegna();
                Dialoghi.messaggio("FINE DELLA DEMO",
                        "Hai attraversato la porta della prima caverna di Ombrafonda.\n"
                                + "Elyra è ancora più in profondità... il viaggio continuerà!");
                navigatore.chiudiPartita(partita);
            }
        }
    }

    /**
     * Chiede conferma e, se il giocatore accetta, abbandona la partita.
     */
    private void abbandona() {
        boolean conferma = Dialoghi.conferma("TORNARE ALLA HOME?",
                "La partita verrà abbandonata e registrata nel diario.");
        if (conferma) {
            partita.abbandona();
            navigatore.chiudiPartita(partita);
        }
    }

    // ------------------------------------------------------------------
    // Disegno
    // ------------------------------------------------------------------

    /**
     * Ridisegna tutta la mappa: pavimento, porta, mostri, eroe e infine il buio.
     */
    private void disegna() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        gc.drawImage(pavimento, 0, 0);

        for (int riga = 0; riga < Mappa.RIGHE; riga++) {
            for (int colonna = 0; colonna < Mappa.COLONNE; colonna++) {
                if (mappa.getCasella(riga, colonna) == TipoCasella.PORTA) {
                    disegnaPorta(gc, riga, colonna);
                }
                Mostro mostro = mappa.getMostro(riga, colonna);
                if (mostro != null) {
                    gc.drawImage(sprite(mostro), colonna * CELLA, riga * CELLA);
                }
            }
        }
        gc.drawImage(eroe, mappa.getColonnaEroe() * CELLA, mappa.getRigaEroe() * CELLA);

        disegnaBuio(gc);
    }

    /**
     * Illumina le caselle intorno all'avventuriero e copre tutte le altre con il buio.
     */
    private void disegnaBuio(GraphicsContext gc) {
        for (int riga = 0; riga < Mappa.RIGHE; riga++) {
            for (int colonna = 0; colonna < Mappa.COLONNE; colonna++) {
                int distanza = distanzaDallEroe(riga, colonna);
                gc.setFill(distanza <= 1 ? LUCE : BUIO);
                gc.fillRect(colonna * CELLA, riga * CELLA, CELLA, CELLA);

                // la porta resta sempre appena visibile, anche nel buio
                if (distanza > 1 && mappa.getCasella(riga, colonna) == TipoCasella.PORTA) {
                    gc.setGlobalAlpha(0.35);
                    disegnaPorta(gc, riga, colonna);
                    gc.setGlobalAlpha(1);
                }
            }
        }
    }

    /**
     * Distanza "a scacchiera" tra una casella e l'avventuriero: le caselle in
     * diagonale contano come vicine, così la luce forma un quadrato.
     */
    private int distanzaDallEroe(int riga, int colonna) {
        return Math.max(Math.abs(riga - mappa.getRigaEroe()),
                Math.abs(colonna - mappa.getColonnaEroe()));
    }

    /**
     * Disegna la porta con dei semplici rettangoli: chiusa e marrone, oppure aperta e luminosa.
     */
    private void disegnaPorta(GraphicsContext gc, int riga, int colonna) {
        double x = colonna * CELLA;
        double y = riga * CELLA;
        // cornice di pietra
        gc.setFill(Color.web("#3b2412"));
        gc.fillRect(x + 6, y + 3, CELLA - 12, CELLA - 3);
        if (mappa.isPortaAperta()) {
            // varco aperto da cui filtra la luce
            gc.setFill(Color.web("#d6a052"));
            gc.fillRect(x + 12, y + 9, CELLA - 24, CELLA - 9);
            gc.setFill(Color.web("#f0e6d2"));
            gc.fillRect(x + 18, y + 15, CELLA - 36, CELLA - 15);
        } else {
            // battente di legno con le assi e la maniglia
            gc.setFill(Color.web("#8b5a2b"));
            gc.fillRect(x + 12, y + 9, CELLA - 24, CELLA - 9);
            gc.setFill(Color.web("#5a3a1e"));
            gc.fillRect(x + 21, y + 9, 3, CELLA - 9);
            gc.setFill(Color.web("#d6a052"));
            gc.fillRect(x + 27, y + 27, 3, 3);
        }
    }

    /**
     * Restituisce lo sprite di un mostro, caricandolo solo la prima volta.
     */
    private Image sprite(Mostro mostro) {
        return spriteMostri.computeIfAbsent(mostro.getNome(), n -> Risorse.ritratto(mostro, CELLA));
    }
}