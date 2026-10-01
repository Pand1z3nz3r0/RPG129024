package it.unicam.cs.mpgc.rpg129024.Model.Avventuriero;

public abstract class Personaggio {
    public String nome;
    public int HP;
    public int AD;

    public Personaggio(String nome, int HP, int AD) {
        if (nome == null || nome.isEmpty() && HP <= 0 && AD <= 0) {
            throw new IllegalArgumentException("valori personaggio non consentiti");
        }
        this.nome = nome;
    }

    public int getHP() {
        return HP;
    }

    public String getNome() {
        return nome;
    }

    public int getAD() {
        return AD;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAD(int AD) {
        this.AD = AD;
    }
}
