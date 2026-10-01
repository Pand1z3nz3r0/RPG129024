package it.unicam.cs.mpgc.rpg129024.model.abilitaSpeciali;

import it.unicam.cs.mpgc.rpg129024.model.avventuriero.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.avventuriero.Personaggio;

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
        int danno = 0;
        danno = (int) (utilizzatore.getAD() * Math.round(this.moltiplicatore));
        bersaglio.subisciDanno(danno);
        return danno;

    }
}
