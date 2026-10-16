package it.unicam.cs.mpgc.rpg129024.model.gioco;

public enum Direzione {
    SU(-1,0),
    GIU(1,0),
    SINISTRA(0,-1),
    DESTRA(0,1);

    private final int riga;
    private final int colonna;

    Direzione(int riga, int colonna) {
        this.riga = riga;
        this.colonna = colonna;
    }

   public int getRiga() {
        return riga;
   }

   public int getColonna() {
        return colonna;
   }
}
