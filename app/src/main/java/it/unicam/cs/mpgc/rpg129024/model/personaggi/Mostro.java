package it.unicam.cs.mpgc.rpg129024.model.personaggi;

/**
 * Nemico che l'avventuriero affronta nel dungeon.
 * <p>
 * I mostri si creano tramite {@link TipoMostro}, che ne definisce nome e
 * statistiche. Al momento un mostro non ha comportamenti propri oltre a
 * quelli di {@link Personaggio}; la classe esiste per distinguere i nemici
 * dall'avventuriero e per poter aggiungere in futuro comportamenti specifici.
 *
 * @author Davide Caliri
 */
public class Mostro extends Personaggio {

    /**
     * Crea un mostro.
     *
     * @param nome  il nome del mostro
     * @param hpMax i punti vita massimi
     * @param ad    il valore di attacco
     * @throws IllegalArgumentException se i valori non sono validi
     */
    public Mostro(String nome, int hpMax, int ad) {
        super(nome, hpMax, ad);
    }

}
