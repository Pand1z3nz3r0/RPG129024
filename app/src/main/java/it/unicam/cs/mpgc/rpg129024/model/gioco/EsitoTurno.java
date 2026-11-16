package it.unicam.cs.mpgc.rpg129024.model.gioco;

import it.unicam.cs.mpgc.rpg129024.model.abilita.Abilita;

/**
 * Resoconto di un turno di battaglia.
 *
 * @param numero        il numero del turno, a partire da 1
 * @param abilitaUsata  l'abilità speciale usata in questo turno, oppure {@code null} se è stato un attacco base
 * @param dannoInflitto i danni inflitti dall'avventuriero
 * @param dannoSubito   i danni subiti dall'avventuriero, 0 se il mostro è stato sconfitto
 * @author Davide Caliri
 */
public record EsitoTurno(int numero, Abilita abilitaUsata, int dannoInflitto, int dannoSubito) {

    /**
     * @return true se in questo turno è stata usata un'abilità speciale
     */
    public boolean haUsatoAbilita() {
        return abilitaUsata != null;
    }
}
