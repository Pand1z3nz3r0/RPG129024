package it.unicam.cs.mpgc.rpg129024.model.gioco;

/**
 * Ricompensa ottenuta dall'avventuriero dopo aver vinto una battaglia.
 *
 * @param bonusHpMax l'aumento degli HP massimi
 * @param bonusAd    l'aumento dell'attacco
 * @param hpCurati   i punti vita effettivamente recuperati
 * @author Davide Caliri
 */
public record Ricompensa(int bonusHpMax, int bonusAd, int hpCurati) {
}