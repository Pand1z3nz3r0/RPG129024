package it.unicam.cs.mpgc.rpg129024.model.gioco;

/**
 * Direzioni di movimento.
 *
 * @author Davide Caliri
 */
public enum Direzione {
    SU(-1,0),
    GIU(1,0),
    SINISTRA(0,-1),
    DESTRA(0,1);

    private final int deltaRiga;
    private final int deltaColonna;

    Direzione(int deltaRiga, int deltaColonna) {
        this.deltaRiga = deltaRiga;
        this.deltaColonna = deltaColonna;
    }

   /**
     * @return il delta di riga associato alla direzione
     */
   public int getDeltaRiga() {
        return deltaRiga;
   }

   /**
     * @return il delta di colonna associato alla direzione
     */
   public int getDeltaColonna() {
        return deltaColonna;
   }
}
