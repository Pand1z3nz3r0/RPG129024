package it.unicam.cs.mpgc.rpg129024.model.personaggi;

/**
 * Catalogo dei tipi di mostro presenti nel gioco.
 * <p>
 * Ogni tipo definisce nome e statistiche iniziali e crea nuove istanze di
 * {@link Mostro}. Per aggiungere un nuovo mostro è sufficiente aggiungere una
 * costante.
 *
 * @author Davide Caliri
 */
public enum TipoMostro {

    BANDITO("Bandito", 50, 8),
    ZOMBIE("Zombie", 70, 10),
    SCHELETRO("Scheletro", 90, 12);

    private final String nome;
    private final int hp;
    private final int ad;

    TipoMostro(String nome, int hp, int ad) {
        this.nome = nome;
        this.hp = hp;
        this.ad = ad;
    }

    /**
     * Crea un nuovo mostro di questo tipo, con i punti vita al massimo.
     * Ogni chiamata restituisce un'istanza diversa.
     *
     * @return un nuovo mostro
     */
    public Mostro crea() {
        return new Mostro(nome, hp, ad);
    }

    /**
     * @return il nome del tipo di mostro
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return i punti vita iniziali
     */
    public int getAd() {
        return ad;
    }

    /**
     * @return il valore di attacco iniziale
     */
    public int getHp() {
        return hp;
    }
}
