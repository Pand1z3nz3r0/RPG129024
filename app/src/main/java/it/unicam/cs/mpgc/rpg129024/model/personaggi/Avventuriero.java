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
        if (!isNomeValido(nome)) {
            throw new IllegalArgumentException("il nome deve avere tra " + LUNGHEZZA_MIN_NOME + " e " + LUNGHEZZA_MAX_NOME + " caratteri");
        }
        this.tipo = tipo;
    }

    /**
     * Verifica se un nome è valido.
     *
     * @param nome il nome da verificare
     * @return true se il nome è valido, false altrimenti
     */
    public static boolean isNomeValido(String nome) {
        if (nome == null) {
            return false;
        }
        int lunghezza = nome.trim().length();
        return lunghezza >= LUNGHEZZA_MIN_NOME && lunghezza <= LUNGHEZZA_MAX_NOME;
    }

    /**
     * Restituisce il tipo dell'avventuriero.
     *
     * @return il tipo
     */
    public TipoAvventuriero getTipo() {
        return tipo;
    }

    /**
     * Restituisce l'abilità speciale dell'avventuriero.
     *
     * @return l'abilità speciale
     */
    public Abilita getAbilita() {
        return tipo.getAbilita();
    }
}
