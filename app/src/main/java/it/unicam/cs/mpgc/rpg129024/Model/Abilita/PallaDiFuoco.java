package it.unicam.cs.mpgc.rpg129024.Model.Abilita;

import it.unicam.cs.mpgc.rpg129024.Model.Avventuriero.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.Model.Avventuriero.Personaggio;

public class PallaDiFuoco implements Abilita {

    private final double moltiplicatore = 1.5;
    @Override
    public String getNome() {
        return "Palla di fuoco";
    }

    @Override
    public String getDescrizione() {
        return "Lancia una palla di fuoco";
    }

    @Override
    public int getRicarica() {
        return 4;
    }

    @Override
    public int usa(Avventuriero utilizzatore, Personaggio bersaglio) {
        return 0;
    }
}
