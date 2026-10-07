package it.unicam.cs.mpgc.rpg129024.model.personaggi;

import it.unicam.cs.mpgc.rpg129024.model.abilita.Abilita;

/**
 * Il personaggio controllato dal giocatore.
 * <p>
 * Statistiche iniziali e abilità speciale derivano dal suo
 * {@link TipoAvventuriero}.
 *
 * @author Davide Caliri
 */
public class Avventuriero extends Personaggio {

    public static final int LUNGHEZZA_MIN_NOME = 3;
    public static final int LUNGHEZZA_MAX_NOME = 15;


    private final TipoAvventuriero tipo;

    /**
     * Crea un avventuriero del tipo indicato.
     *
     * @param nome il nome scelto dal giocatore
     * @param tipo il tipo di avventuriero
     * @throws IllegalArgumentException se il nome non rispetta la lunghezza consentita
     */
    public Avventuriero(String nome, TipoAvventuriero tipo) {
        super(nome, tipo.getHp(), tipo.getAd());
        int lunghezza = nome.trim().length();
        if (lunghezza < LUNGHEZZA_MIN_NOME || lunghezza > LUNGHEZZA_MAX_NOME) {
            throw new IllegalArgumentException("il nome deve avere tra " + LUNGHEZZA_MIN_NOME + " e " + LUNGHEZZA_MAX_NOME + " caratteri");
        }
        this.tipo = tipo;
    }

    public TipoAvventuriero getTipo() {
        return tipo;
    }

    public Abilita getAbilita() {
        return tipo.getAbilita();
    }
}
