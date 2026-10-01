package it.unicam.cs.mpgc.rpg129024.Model.Avventuriero;

import it.unicam.cs.mpgc.rpg129024.Model.Abilita.Abilita;

public class Mostro extends Personaggio {
    private final Abilita abilita;

    public Mostro(String nome, int HP, int AD, Abilita abilita) {
        super(nome, HP, AD);
        if(abilita == null) throw new NullPointerException("Il Abilita nuova!");
        this.abilita = abilita;
    }

}
