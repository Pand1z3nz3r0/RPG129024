package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.model.gioco.Battaglia;
import it.unicam.cs.mpgc.rpg129024.model.gioco.EsitoTurno;
import it.unicam.cs.mpgc.rpg129024.model.gioco.Partita;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Personaggio;
import it.unicam.cs.mpgc.rpg129024.view.Narratore;
import it.unicam.cs.mpgc.rpg129024.view.Navigatore;
import it.unicam.cs.mpgc.rpg129024.view.Pagina;
import it.unicam.cs.mpgc.rpg129024.view.Risorse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Locale;

/**
 * Schermata di una battaglia a turni tra l'avventuriero e un mostro.
 * <p>
 * Ad ogni turno il giocatore sceglie tra l'attacco base e l'abilità
 * speciale, che si attiva solo dopo un certo numero di attacchi. Il
 * resoconto di ogni turno è scritto nel registro in basso. A battaglia
 * finita compare il bottone CONTINUA, che passa alla schermata di vittoria
 * o di sconfitta.
 *
 * @author Davide Caliri
 */
public class PaginaBattaglia implements Pagina {

    private static final double LATO_SPRITE = 144;
    private static final double LARGHEZZA_BARRA = 220;

    private final Battaglia battaglia;
    private final Avventuriero avventuriero;
    private final Mostro mostro;
    private final StackPane radice;

    private final Scheda schedaEroe;
    private final Scheda schedaMostro;
    private final TextArea registro = new TextArea();
    private final Button attacca = new Button("ATTACCA");
    private final Button abilita = new Button();
    private final Button continua = new Button("CONTINUA");

    /**
     * Costruisce la schermata della battaglia in corso.
     *
     * @param navigatore il navigatore, per passare alla vittoria o alla sconfitta
     * @param partita    la partita, con una battaglia in corso
     * @throws IllegalStateException se nella partita non c'è una battaglia in corso
     */
    public PaginaBattaglia(Navigatore navigatore, Partita partita) {
        if (!partita.isInBattaglia()) {
            throw new IllegalStateException("Non c'è nessuna battaglia in corso");
        }
        this.battaglia = partita.getBattaglia();
        this.avventuriero = battaglia.getAvventuriero();
        this.mostro = battaglia.getMostro();

        Label titolo = new Label("BATTAGLIA");
        titolo.getStyleClass().setAll("titolo-pagina", "titolo-medio");

        // le due schede, una di fronte all'altra
        schedaEroe = new Scheda(avventuriero,
                Risorse.ritratto(avventuriero.getTipo(), LATO_SPRITE), "barra-eroe");
        schedaMostro = new Scheda(mostro, Risorse.ritratto(mostro, LATO_SPRITE), "barra-mostro");
        Label vs = new Label("VS");
        vs.getStyleClass().add("vs");
        HBox combattenti = new HBox(60, schedaEroe.contenuto, vs, schedaMostro.contenuto);
        combattenti.setAlignment(Pos.CENTER);

        // registro dei turni
        registro.setEditable(false);
        registro.setWrapText(true);
        registro.setFocusTraversable(false);
        registro.setPrefRowCount(5);
        registro.setMaxWidth(760);
        registro.getStyleClass().add("log");
        registro.setText(Narratore.inizioBattaglia(mostro));

        // bottoni
        attacca.getStyleClass().add("bottone-pixel");
        abilita.getStyleClass().add("bottone-pixel");
        continua.getStyleClass().add("bottone-pixel");
        attacca.setOnAction(e -> mostraTurno(battaglia.attaccoBase()));
        abilita.setOnAction(e -> mostraTurno(battaglia.usaAbilita()));
        continua.setOnAction(e -> partita.concludiBattaglia().ifPresentOrElse(
                ricompensa -> navigatore.vaiAllaPaginaVittoria(partita, ricompensa),
                () -> navigatore.vaiAllaPaginaSconfitta(partita)));
        // CONTINUA è nascosto finché la battaglia non finisce
        mostraContinua(false);

        HBox bottoni = new HBox(16, attacca, abilita, continua);
        bottoni.setAlignment(Pos.CENTER);

        VBox contenuto = new VBox(14, titolo, combattenti, registro, bottoni);
        contenuto.setAlignment(Pos.TOP_CENTER);
        contenuto.setPadding(new Insets(0, 0, 20, 0));

        radice = new StackPane(new javafx.scene.image.ImageView(Risorse.immagine("/img/sfondo.png")), contenuto);
        aggiorna();
    }

    @Override
    public Parent getRadice() {
        return radice;
    }

    /**
     * Scrive il turno nel registro e aggiorna la schermata.
     */
    private void mostraTurno(EsitoTurno esito) {
        registro.appendText("\n" + Narratore.descriviTurno(esito, avventuriero, mostro));
        if (battaglia.isFinita()) {
            registro.appendText("\n" + Narratore.fineBattaglia(battaglia));
        }
        aggiorna();
    }

    /**
     * Aggiorna barre della vita, statistiche e bottoni in base allo stato della battaglia.
     */
    private void aggiorna() {
        schedaEroe.aggiorna();
        schedaMostro.aggiorna();

        String nomeAbilita = avventuriero.getAbilita().getNome().toUpperCase(Locale.ROOT);
        abilita.setText(battaglia.isAbilitaPronta()
                ? nomeAbilita + "!"
                : nomeAbilita + " (" + battaglia.getAttacchiMancanti() + ")");
        abilita.setDisable(!battaglia.isAbilitaPronta());

        if (battaglia.isFinita()) {
            mostraContinua(true);
        }
    }

    /**
     * Mostra CONTINUA al posto dei bottoni di attacco, o il contrario.
     */
    private void mostraContinua(boolean mostra) {
        for (Button bottone : new Button[]{attacca, abilita}) {
            bottone.setVisible(!mostra);
            bottone.setManaged(!mostra);
        }
        continua.setVisible(mostra);
        continua.setManaged(mostra);
    }

    /**
     * La scheda di un personaggio: sprite, nome, barra della vita e statistiche.
     * <p>
     * È una classe interna perché serve solo a questa pagina, due volte: una
     * per l'avventuriero e una per il mostro.
     */
    private static final class Scheda {

        private final Personaggio personaggio;
        private final VBox contenuto;
        private final ProgressBar barra = new ProgressBar();
        private final Label vita = new Label();
        private final Label attacco = new Label();

        Scheda(Personaggio personaggio, Image sprite, String classeBarra) {
            this.personaggio = personaggio;

            Label nome = new Label(personaggio.getNome().toUpperCase(Locale.ROOT));
            nome.getStyleClass().add("nome-card");

            barra.setPrefWidth(LARGHEZZA_BARRA);
            barra.getStyleClass().addAll("barra-vita", classeBarra);
            vita.getStyleClass().add("info");
            attacco.getStyleClass().add("info");

            contenuto = new VBox(8, new ImageView(sprite), nome, barra, vita, attacco);
            contenuto.setAlignment(Pos.CENTER);
        }

        void aggiorna() {
            barra.setProgress((double) personaggio.getHp() / personaggio.getHpMax());
            vita.setText("HP " + personaggio.getHp() + "/" + personaggio.getHpMax());
            attacco.setText("AD " + personaggio.getAd());
        }
    }
}