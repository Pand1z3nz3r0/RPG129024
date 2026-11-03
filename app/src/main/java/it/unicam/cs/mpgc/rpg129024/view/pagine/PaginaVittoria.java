package it.unicam.cs.mpgc.rpg129024.view.pagine;

import it.unicam.cs.mpgc.rpg129024.model.gioco.Partita;
import it.unicam.cs.mpgc.rpg129024.model.gioco.Ricompensa;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.view.Narratore;
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

/**
 * Schermata mostrata dopo una battaglia vinta.
 * <p>
 * Riassume i potenziamenti ottenuti, le nuove statistiche dell'avventuriero
 * e quanti mostri restano prima che la porta si apra.
 *
 * @author Davide Caliri
 */
public class PaginaVittoria implements Pagina {

    private final StackPane radice;

    /**
     * Costruisce la schermata di vittoria.
     *
     * @param navigatore il navigatore, per tornare alla mappa
     * @param partita    la partita in corso
     * @param ricompensa i potenziamenti ottenuti con la vittoria
     */
    public PaginaVittoria(Navigatore navigatore, Partita partita, Ricompensa ricompensa) {
        Avventuriero avventuriero = partita.getAvventuriero();

        Label titolo = new Label("VITTORIA!");
        titolo.getStyleClass().setAll("titolo-pagina");

        ImageView ritratto = new ImageView(Risorse.ritratto(avventuriero.getTipo(), 144));

        Label bonus = new Label(Narratore.descriviRicompensa(ricompensa));
        bonus.getStyleClass().add("testo-storia");
        bonus.setTextAlignment(TextAlignment.CENTER);

        Label statistiche = new Label("HP " + avventuriero.getHp() + "/" + avventuriero.getHpMax()
                + "   AD " + avventuriero.getAd());
        statistiche.getStyleClass().add("statistiche");

        int rimasti = partita.getMappa().getMostriRimasti();
        Label porta = new Label(rimasti == 0
                ? "LA PORTA ORA È APERTA!"
                : "MOSTRI RIMASTI: " + rimasti);
        porta.getStyleClass().add("sottotitolo");

        VBox riquadro = new VBox(14, bonus, statistiche, porta);
        riquadro.getStyleClass().add("riquadro");
        riquadro.setAlignment(Pos.CENTER);
        riquadro.setMaxWidth(560);

        Button torna = new Button("TORNA NELLA CAVERNA");
        torna.getStyleClass().add("bottone-pixel");
        torna.setOnAction(e -> navigatore.vaiAllaPaginaMappa(partita));

        VBox contenuto = new VBox(18, titolo, ritratto, riquadro, torna);
        contenuto.setAlignment(Pos.CENTER);

        radice = new StackPane(new ImageView(Risorse.immagine("/img/sfondo.png")), contenuto);
    }

    @Override
    public Parent getRadice() {
        return radice;
    }
}