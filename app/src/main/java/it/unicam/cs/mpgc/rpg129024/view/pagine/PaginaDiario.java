package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.model.gioco.EsitoPartita;
import it.unicam.cs.mpgc.rpg129024.persistence.DiarioRepository;
import it.unicam.cs.mpgc.rpg129024.persistence.PersistenzaException;
import it.unicam.cs.mpgc.rpg129024.persistence.VoceDiario;
import it.unicam.cs.mpgc.rpg129024.view.Narratore;
import it.unicam.cs.mpgc.rpg129024.view.Navigatore;
import it.unicam.cs.mpgc.rpg129024.view.Pagina;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Locale;

/**
 * Schermata del diario: l'elenco delle partite concluse, dalla più recente.
 * <p>
 * Per ogni partita mostra nome e tipo dell'avventuriero, i nemici sconfitti
 * e come si è conclusa. Le voci sono lette tramite un
 * {@link DiarioRepository}, quindi la pagina non sa come e dove sono salvate.
 *
 * @author Davide Caliri
 */
public class PaginaDiario implements Pagina {

    private final StackPane radice;

    /**
     * Costruisce la schermata del diario.
     *
     * @param navigatore il navigatore, per tornare alla home
     * @param diario     da dove leggere le partite concluse
     */
    public PaginaDiario(Navigatore navigatore, DiarioRepository diario) {
        Label titolo = new Label("DIARIO");
        titolo.getStyleClass().setAll("titolo-pagina");

        VBox elenco = new VBox(10);
        elenco.setAlignment(Pos.TOP_CENTER);
        elenco.setPadding(new Insets(0, 16, 0, 0));
        elenco.getChildren().addAll(creaVoci(diario));

        ScrollPane scroll = new ScrollPane(elenco);
        scroll.setMaxWidth(720);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.getStyleClass().add("scroll-guida");

        Button home = new Button("HOME");
        home.getStyleClass().addAll("bottone-pixel", "bottone-grande");
        home.setOnAction(e -> navigatore.vaiAllaPaginaHome());

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

    /**
     * Crea una riga per ogni partita, dalla più recente. Se il diario è vuoto
     * o non si riesce a leggere, restituisce un solo messaggio.
     */
    private List<Node> creaVoci(DiarioRepository diario) {
        List<VoceDiario> voci;
        try {
            voci = diario.caricaTutte();
        } catch (PersistenzaException e) {
            return List.of(messaggio("IMPOSSIBILE LEGGERE IL DIARIO", "errore"));
        }
        if (voci.isEmpty()) {
            return List.of(messaggio("NESSUNA AVVENTURA, PER ORA.\nIL DIARIO TI ASPETTA!", "sottotitolo"));
        }
        // le voci sono salvate in ordine di tempo: si mostrano dalla più recente
        return voci.reversed().stream()
                .map(this::creaVoce)
                .toList();
    }

    /**
     * Crea la riga di una partita: sprite del tipo, nome, nemici sconfitti ed esito.
     */
    private Node creaVoce(VoceDiario voce) {
        ImageView ritratto = new ImageView(Risorse.ritratto(voce.tipo(), 48));

        Label nome = new Label(voce.nomeAvventuriero().toUpperCase(Locale.ROOT)
                + " - " + voce.tipo().getNome().toUpperCase(Locale.ROOT));
        nome.getStyleClass().add("nome-voce");

        Label nemici = new Label("NEMICI SCONFITTI: " + voce.nemiciSconfitti());
        nemici.getStyleClass().add("info");

        Label esito = new Label(Narratore.descriviEsito(voce.esito()));
        esito.getStyleClass().add(classeEsito(voce.esito()));

        VBox testi = new VBox(6, nome, nemici, esito);
        HBox riga = new HBox(16, ritratto, testi);
        riga.setAlignment(Pos.CENTER_LEFT);
        riga.getStyleClass().add("voce-diario");
        return riga;
    }

    /**
     * Ogni esito ha il suo colore nel foglio di stile.
     */
    private String classeEsito(EsitoPartita esito) {
        return switch (esito) {
            case COMPLETATA -> "esito-completata";
            case SCONFITTA -> "esito-sconfitta";
            case ABBANDONATA -> "esito-abbandonata";
        };
    }

    private Label messaggio(String testo, String classe) {
        Label etichetta = new Label(testo);
        etichetta.getStyleClass().add(classe);
        etichetta.setPadding(new Insets(40, 0, 0, 0));
        return etichetta;
    }
}
