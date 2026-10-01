package it.unicam.cs.mpgc.rpg129024.Model.Abilita;

public class Furia implements Abilita {
    @Override
    public String getNome() {
        return "Furia";
    }

    @Override
    public String getDescrizione() {
        return "Il prossimo attacco è fuorioso e infligge più danni";
    }

    @Override
    public int getRicarica() {
        return 3;
    }
}
