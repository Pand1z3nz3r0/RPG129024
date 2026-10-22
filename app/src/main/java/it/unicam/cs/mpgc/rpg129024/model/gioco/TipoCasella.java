package it.unicam.cs.mpgc.rpg129024.model.gioco;

/**
 * Tipi di casella della mappa.
 *
 * @author Davide Caliri
 */
public enum TipoCasella {
    PAVIMENTO,
    BUIO,
    PORTA;

    /**
     * @return {@code true} se l'avventuriero può entrare nella casella
     */
    public boolean isPercorribile() {
        return this != BUIO;
    }

}
