package it.unicam.cs.mpgc.rpg129024.Model.Abilita;

import it.unicam.cs.mpgc.rpg129024.Model.Avventuriero.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.Model.Avventuriero.Personaggio;

public class Furia implements Abilita {
    private final double moltiplicatore = 1.7;
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

    @Override
    public int usa(Avventuriero utilizzatore, Personaggio bersaglio) {
        return 0;
    }
}
