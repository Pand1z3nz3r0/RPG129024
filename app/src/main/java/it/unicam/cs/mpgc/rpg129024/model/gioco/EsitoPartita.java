package it.unicam.cs.mpgc.rpg129024.model.gioco;

/**
 * Modo in cui si è conclusa una partita.
 *
 * @author Davide Caliri
 */
public enum EsitoPartita {
    /**
     * L'avventuriero ha raggiunto la porta.
     */
    COMPLETATA,
    /**
     * L'avventuriero è stato sconfitto.
     */
    SCONFITTA,
    /**
     * Il giocatore è tornato alla home durante la partita.
     */
    ABBANDONATA
}