package it.unicam.cs.mpgc.rpg129024.model.avventuriero;

import it.unicam.cs.mpgc.rpg129024.model.abilitaSpeciali.Abilita;

public class Avventuriero extends Personaggio {

    private final Abilita abilita;
    private final String descrizioneAvventuriero;
    private final boolean disponibilita;


    public Avventuriero(String nome, String descrizone,int HP, int AD, Abilita abilita, boolean disponibile) {
        super(nome, HP, AD);
        if(abilita == null) throw new NullPointerException("abilità non valida");
        this.abilita = abilita;
        this.descrizioneAvventuriero = descrizone;
        this.disponibilita = disponibile;
    }

    public Abilita getAbilita() {
        return this.abilita;
    }

    public String getDescrizioneAvventuriero() {
        return this.descrizioneAvventuriero;
    }
    public boolean isDisponibilita() {
        return this.disponibilita;
    }

}
