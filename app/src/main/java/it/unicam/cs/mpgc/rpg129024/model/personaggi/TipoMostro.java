package it.unicam.cs.mpgc.rpg129024.model.personaggi;

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

    public Mostro crea() {
        return new Mostro(nome, hp, ad);
    }

    public String getNome() {
        return nome;
    }

    public int getAd() {
        return ad;
    }

    public int getHp() {
        return hp;
    }
}
