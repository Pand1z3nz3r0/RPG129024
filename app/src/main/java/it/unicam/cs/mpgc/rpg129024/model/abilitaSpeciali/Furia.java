package it.unicam.cs.mpgc.rpg129024.model.abilitaSpeciali;

import it.unicam.cs.mpgc.rpg129024.model.avventuriero.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.avventuriero.Personaggio;

public class Furia implements Abilita {
    private final double moltiplicatore = 2.0;
    @Override
    public String getNome() {
        return "Furia";
    }

    @Override
    public String getDescrizione() {
        return "un colpo furioso che infligge il doppio dei danni";
    }

    @Override
    public int getRicarica() {
        return 3;
    }

    @Override
    public int usa(Avventuriero utilizzatore, Personaggio bersaglio) {
            int danno = 0;
            danno = (int) (utilizzatore.getAD() * Math.round(this.moltiplicatore));
            bersaglio.subisciDanno(danno);
            return danno;

    }
}
