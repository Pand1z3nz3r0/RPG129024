package it.unicam.cs.mpgc.rpg129024.view;

import it.unicam.cs.mpgc.rpg129024.model.gioco.Battaglia;
import it.unicam.cs.mpgc.rpg129024.model.gioco.EsitoPartita;
import it.unicam.cs.mpgc.rpg129024.model.gioco.EsitoTurno;
import it.unicam.cs.mpgc.rpg129024.model.gioco.Ricompensa;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Mostro;

/**
 * Trasforma i dati restituiti dal modello nei testi mostrati al giocatore.
 * <p>
 * Il modello non costruisce stringhe: restituisce solo dati, come
 * {@link EsitoTurno} o {@link Ricompensa}. Tenere qui tutti i testi permette
 * di cambiarli, o di tradurli, senza toccare la logica del gioco.
 *
 * @author Davide Caliri
 */
public final class Narratore {

    private Narratore() {
        // classe di sole funzioni statiche: non si istanzia
    }

    /**
     * @param mostro il mostro incontrato
     * @return la frase che apre la battaglia
     */
    public static String inizioBattaglia(Mostro mostro) {
        return "Dall'oscurità emerge: " + mostro.getNome() + "!";
    }

    /**
     * Descrive un turno di battaglia: l'attacco dell'avventuriero e, se il
     * mostro è sopravvissuto, il suo contrattacco.
     *
     * @param esito        il risultato del turno
     * @param avventuriero l'avventuriero in battaglia
     * @param mostro       il mostro in battaglia
     * @return la descrizione del turno, su una o due righe
     */
    public static String descriviTurno(EsitoTurno esito, Avventuriero avventuriero, Mostro mostro) {
        String azione = esito.abilitaUsata()
                ? " usa " + avventuriero.getAbilita().getNome() + "!"
                : " attacca.";
        String testo = "Turno " + esito.numero() + " - " + avventuriero.getNome() + azione
                + " " + mostro.getNome() + " subisce " + esito.dannoInflitto() + " danni.";
        if (esito.dannoSubito() > 0) {
            testo += "\n" + mostro.getNome() + " contrattacca: "
                    + avventuriero.getNome() + " subisce " + esito.dannoSubito() + " danni.";
        }
        return testo;
    }

    /**
     * @param battaglia una battaglia conclusa
     * @return la frase che chiude la battaglia
     */
    public static String fineBattaglia(Battaglia battaglia) {
        String mostro = battaglia.getMostro().getNome();
        return battaglia.isVittoria()
                ? mostro + " è stato sconfitto!"
                : battaglia.getAvventuriero().getNome() + " è stato sconfitto da " + mostro + "...";
    }

    /**
     * @param ricompensa i potenziamenti ottenuti con una vittoria
     * @return l'elenco dei potenziamenti, uno per riga
     */
    public static String descriviRicompensa(Ricompensa ricompensa) {
        return "+" + ricompensa.bonusHpMax() + " HP MASSIMI\n"
                + "+" + ricompensa.bonusAd() + " AD\n"
                + "+" + ricompensa.hpCurati() + " HP RECUPERATI";
    }

    /**
     * @param esito come si è conclusa una partita
     * @return l'esito da mostrare nel diario
     */
    public static String descriviEsito(EsitoPartita esito) {
        return switch (esito) {
            case COMPLETATA -> "DEMO COMPLETATA";
            case SCONFITTA -> "CADUTO IN BATTAGLIA";
            case ABBANDONATA -> "ABBANDONATA";
        };
    }
}
