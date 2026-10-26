package it.unicam.cs.mpgc.rpg129024.model.gioco;

/**
 * Tipi di casella della mappa.
 * <p>
 * Il buio della caverna non è un tipo di casella: è solo un effetto grafico
 * della vista, che mostra al giocatore le caselle vicine all'eroe.
 *
 * @author Davide Caliri
 */
public enum TipoCasella {
    /**
     * Casella percorribile.
     */
    PAVIMENTO,
    /**
     * Casella non percorribile (non usata nella demo, prevista per mappe future).
     */
    MURO,
    /**
     * Uscita della mappa: si apre quando tutti i mostri sono stati sconfitti.
     */
    PORTA;

    /**
     * @return {@code true} se l'avventuriero può entrare nella casella
     */
    public boolean isPercorribile() {
        return this != MURO;
    }
}
