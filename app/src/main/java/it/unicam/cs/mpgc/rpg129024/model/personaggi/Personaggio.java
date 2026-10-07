package it.unicam.cs.mpgc.rpg129024.model.personaggi;

public abstract class Personaggio {

    private final String nome;
    private int hp;
    private int hpMax;
    private int ad;

    protected Personaggio(String nome, int hp, int ad) {
        if (nome == null || nome.isBlank() || hp <= 0 || ad <= 0) {
            throw new IllegalArgumentException("valori personaggio non consentiti");
        }
        this.nome = nome;
        this.hp = hp;
        this.hpMax = hp;
        this.ad = ad;
    }

    public void subisciDanno(int danno){
        if(danno<=0){
            throw new IllegalArgumentException("il danno deve essere maggiore di 0");
        }
        this.hp = Math.max(0, getHp() - danno);
    }
    public void cura(int recuperoHp){
        if(recuperoHp<=0){
            throw new IllegalArgumentException("la cura non può minore o uguale di 0");
        }
        this.hp = Math.min(getHpMax(), this.getHp() + recuperoHp);
    }

    public void potenzia(int potenziamentoHp, int potenziamentoAd){
        if(potenziamentoAd<=0 || potenziamentoHp<=0){
            throw new IllegalArgumentException(" i potenziamenti non devono essere negativi o uguali a 0");
        }
        this.hpMax = getHpMax() + potenziamentoHp;
        this.ad = getAd() + potenziamentoAd;
    }
    public boolean isVivo(){
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
