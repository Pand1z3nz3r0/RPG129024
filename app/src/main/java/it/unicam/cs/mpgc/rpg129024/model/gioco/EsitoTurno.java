package it.unicam.cs.mpgc.rpg129024.model.gioco;

    /**
     * Resoconto di un turno di battaglia.
     *
     * @param numero        il numero del turno, a partire da 1
     * @param abilitaUsata  {@code true} se l'avventuriero ha usato l'abilità speciale
     * @param dannoInflitto i danni inflitti dall'avventuriero
     * @param dannoSubito   i danni subiti dall'avventuriero, 0 se il mostro è stato sconfitto
     * @author Davide Caliri
     */
    public record EsitoTurno(int numero, boolean abilitaUsata, int dannoInflitto, int dannoSubito) {
    }
