package it.unicam.cs.mpgc.rpg129024.model.abilita;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Personaggio;


/**
 * Abilità del Barbaro: un colpo furioso che infligge il danno dei danni dell'attacco base.
 *
 * @author Davide Caliri
 */
public class Furia implements Abilita {

    /** Moltiplicatore applicato all'AD dell'utilizzatore. */
    private static final double MOLTIPLICATORE = 2.0;

    /** Attacchi base necessari prima di poter usare l'abilità. */
    private static final int RICARICA = 3;

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
        return RICARICA;
    }

    /**
     * Infligge al bersaglio danni pari all'AD dell'utilizzatore moltiplicato
     * per {@value #MOLTIPLICATORE}, arrotondati all'intero più vicino.
     *
     * @param utilizzatore l'avventuriero che usa l'abilità
     * @param bersaglio    il personaggio colpito
     * @return i danni inflitti
     */
    @Override
    public int usa(Avventuriero utilizzatore, Personaggio bersaglio) {
            int danno = 0;
            danno = (int) (utilizzatore.getAd() * Math.round(MOLTIPLICATORE));
            bersaglio.subisciDanno(danno);
            return danno;

    }
}
