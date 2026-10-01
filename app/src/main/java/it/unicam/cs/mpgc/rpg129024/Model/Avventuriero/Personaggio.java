package it.unicam.cs.mpgc.rpg129024.Model.Avventuriero;

public abstract class Personaggio {
    private final String nome;
    private int hp;
    private int hpMax;
    private int ad;

    protected Personaggio(String nome, int hp, int ad) {
        if (nome == null || nome.isEmpty() || hp <= 0 || ad <= 0) {
            throw new IllegalArgumentException("valori personaggio non consentiti");
        }
        this.nome = nome;
        this.hp = hp;
        this.hpMax = hp;
        this.ad = ad;
    }

    public int subisciDanno(int danno){
        if(danno<=0){
            throw new IllegalArgumentException("il danno deve essere maggiore di 0");
        }
         return this.hp = Math.max(0, getHP() - danno);
    }
    public void cura(int recuperoHp){
        if(recuperoHp<=0){
            throw new IllegalArgumentException("la recuperoHp non può essere uguale o minore di 0");
        }
        this.hp = Math.min(getHP(), this.getHP() + recuperoHp);
    }

    public void potenziamento(int potenziamentoHP, int potenziamentoAD){
        if(potenziamentoAD<=0 || potenziamentoHP<=0){
            throw new IllegalArgumentException(" i potenziamenti non devono essere negativi");
        }
        this.hp = getHP() + potenziamentoHP;
        this.ad = getAD() + potenziamentoAD;
    }
    public boolean isVivo(){
        return getHP() > 0;
    }
    public String getNome() {
        return nome;
    }

    public int getHP() {
        return hp;
    }

    public int getAD() {
        return ad;
    }

    public int getHpMax() {
        return hpMax;
    }
}
