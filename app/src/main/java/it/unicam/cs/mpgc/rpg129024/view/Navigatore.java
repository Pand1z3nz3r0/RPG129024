package it.unicam.cs.mpgc.rpg129024.view;

import it.unicam.cs.mpgc.rpg129024.model.gioco.Partita;
import it.unicam.cs.mpgc.rpg129024.model.gioco.Ricompensa;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.persistence.DiarioRepository;
import it.unicam.cs.mpgc.rpg129024.persistence.PersistenzaException;
import it.unicam.cs.mpgc.rpg129024.persistence.VoceDiario;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaBattaglia;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaDiario;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaGuida;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaHome;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaMappa;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaScelta;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaSconfitta;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaStoria;
import it.unicam.cs.mpgc.rpg129024.view.pagine.PaginaVittoria;
import javafx.scene.Scene;

import java.util.Objects;

/**
 * Gestisce il passaggio da una schermata all'altra.
 * <p>
 * Il gioco usa una sola {@link Scene}: per cambiare schermata il navigatore
 * crea la pagina richiesta e ne sostituisce il contenuto con
 * {@link Scene#setRoot}. Le pagine non si conoscono tra loro: quando
 * l'utente vuole spostarsi, chiedono al navigatore di farlo.
 * <p>
 * Il navigatore registra anche nel diario le partite concluse.
 *
 * @author Davide Caliri
 */
public class Navigatore {

    private final Scene scena;
    private final DiarioRepository diario;

    /**
     * Crea il navigatore.
     *
     * @param scena  la scena della finestra, in cui mostrare le pagine
     * @param diario dove registrare le partite concluse
     * @throws NullPointerException se uno dei parametri è {@code null}
     */
    public Navigatore(Scene scena, DiarioRepository diario) {
        this.scena = Objects.requireNonNull(scena, "La scena non può essere null");
        this.diario = Objects.requireNonNull(diario, "Il diario non può essere null");
    }

    /** Mostra la schermata iniziale. */
    public void vaiAllaPaginaHome() {
        mostra(new PaginaHome(this));
    }

    /** Mostra la guida di gioco. */
    public void vaiAllaPaginaGuida() {
        mostra(new PaginaGuida(this));
    }

    /** Mostra il diario delle avventure passate. */
    public void vaiAllaPaginaDiario() {
        mostra(new PaginaDiario(this, diario));
    }

    /**
     * Mostra la scelta del tipo di avventuriero.
     *
     * @param nome il nome scelto dal giocatore nella home
     */
    public void vaiAllaPaginaScelta(String nome) {
        mostra(new PaginaScelta(this, nome));
    }

    /**
     * Mostra il racconto che introduce la partita.
     *
     * @param avventuriero l'avventuriero appena creato
     */
    public void vaiAllaPaginaStoria(Avventuriero avventuriero) {
        mostra(new PaginaStoria(this, avventuriero));
    }

    /**
     * Mostra la mappa della caverna.
     *
     * @param partita la partita in corso
     */
    public void vaiAllaPaginaMappa(Partita partita) {
        mostra(new PaginaMappa(this, partita));
    }

    /**
     * Mostra la battaglia in corso nella partita.
     *
     * @param partita la partita, con una battaglia in corso
     */
    public void vaiAllaPaginaBattaglia(Partita partita) {
        mostra(new PaginaBattaglia(this, partita));
    }

    /**
     * Mostra il riepilogo di una battaglia vinta.
     *
     * @param partita    la partita in corso
     * @param ricompensa i potenziamenti ottenuti con la vittoria
     */
    public void vaiAllaPaginaVittoria(Partita partita, Ricompensa ricompensa) {
        mostra(new PaginaVittoria(this, partita, ricompensa));
    }

    /**
     * Registra la partita persa nel diario e mostra la schermata di sconfitta.
     *
     * @param partita la partita appena persa
     */
    public void vaiAllaPaginaSconfitta(Partita partita) {
        registraNelDiario(partita);
        mostra(new PaginaSconfitta(this, partita));
    }

    /**
     * Registra nel diario una partita conclusa, perché completata o
     * abbandonata, e torna alla home.
     *
     * @param partita la partita conclusa
     */
    public void chiudiPartita(Partita partita) {
        registraNelDiario(partita);
        vaiAllaPaginaHome();
    }

    /**
     * Salva la partita nel diario. Se il salvataggio non riesce, avvisa il
     * giocatore senza interrompere il gioco.
     */
    private void registraNelDiario(Partita partita) {
        try {
            diario.salva(VoceDiario.daPartita(partita));
        } catch (PersistenzaException e) {
            Dialoghi.messaggio("DIARIO", "Non è stato possibile salvare la partita nel diario.");
        }
    }

    private void mostra(Pagina pagina) {
        scena.setRoot(pagina.getRadice());
        pagina.mostrata();
    }
}
