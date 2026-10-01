package it.unicam.cs.mpgc.rpg129024.Model.Avventuriero;

import it.unicam.cs.mpgc.rpg129024.Model.Abilita.Abilita;

public class Avventuriero extends Personaggio {

    private final Abilita abilita;

    public Avventuriero(String nome, int HP, int AD, Abilita abilita) {
        super(nome, HP, AD);
        if(abilita == null) throw new NullPointerException("abilità non valida");
        this.abilita = abilita;
    }

    public Abilita getAbilita() {
        return this.abilita;
    }

}
