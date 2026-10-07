package it.unicam.cs.mpgc.rpg129024.model.personaggi;

/**
 * Personaggio che può prendere parte a una battaglia.
 * <p>
 * Gestisce le caratteristiche comuni a tutti i personaggi (nome, punti vita
 * e attacco) e garantisce che restino sempre valide: gli HP non scendono mai
 * sotto zero e non superano mai gli HP massimi.
 *
 * @author Davide Caliri
 */
public abstract class Personaggio {

    private final String nome;
    private int hp;
    private int hpMax;
    private int ad;

    /**
     * Crea un personaggio con i punti vita al massimo.
     *
     * @param nome  il nome del personaggio
     * @param hpMax i punti vita massimi, maggiori di zero
     * @param ad    il valore di attacco, maggiore di zero
     * @throws IllegalArgumentException se il nome è vuoto o se HP o AD non
     *                                  sono positivi
     */
    protected Personaggio(String nome, int hpMax, int ad) {
        if (nome == null || nome.isBlank() || hpMax <= 0 || ad <= 0) {
            throw new IllegalArgumentException("valori personaggio non consentiti");
        }
        this.nome = nome;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.ad = ad;
    }

    /**
     * Riduce i punti vita del danno indicato. Gli HP non scendono mai sotto
     * zero.
     *
     * @param danno il danno subito, non negativo
     * @throws IllegalArgumentException se il danno è negativo
     */
    public void subisciDanno(int danno) {
        if (danno < 0) {
            throw new IllegalArgumentException("il danno deve essere maggiore di 0");
        }
        this.hp = Math.max(0, getHp() - danno);
    }

    /**
     * Recupera punti vita. Gli HP non superano mai gli HP massimi.
     *
     * @param recuperoHp i punti vita da recuperare, non negativi
     * @throws IllegalArgumentException se la quantità è negativa
     */
    public void cura(int recuperoHp) {
        if (recuperoHp < 0) {
            throw new IllegalArgumentException("la cura non può minore 0");
        }
        this.hp = Math.min(getHpMax(), this.getHp() + recuperoHp);
    }

    /**
     * Aumenta in modo permanente gli HP massimi e l'attacco.
     * <p>
     * Gli HP attuali non cambiano: per recuperare vita si usa
     * {@link #cura(int)}.
     *
     * @param bonusHpMax l'aumento degli HP massimi, non negativo
     * @param bonusAd    l'aumento dell'attacco, non negativo
     * @throws IllegalArgumentException se uno dei bonus è negativo
     */
    public void potenzia(int bonusHpMax, int bonusAd) {
        if (bonusAd < 0 || bonusHpMax < 0) {
            throw new IllegalArgumentException(" i potenziamenti non devono essere minori di 0");
        }
        this.hpMax = getHpMax() + bonusHpMax;
        this.ad = getAd() + bonusAd;
    }

    /**
     * Indica se il personaggio è ancora in vita.
     *
     * @return {@code true} se gli HP sono maggiori di zero
     */
    public boolean isVivo() {
        return getHp() > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getAd() {
        return ad;
    }

    public int getHpMax() {
        return hpMax;
    }
}
