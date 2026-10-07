package it.unicam.cs.mpgc.rpg129024.model.abilita;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Personaggio;

/**
 * Abilità del Mago: una sfera di fiamme che infligge due volte e mezzo i
 * danni dell'attacco base, ma richiede una ricarica più lunga.
 *
 * @author Davide Caliri
 */
public class PallaDiFuoco implements Abilita {
    /**
     * Moltiplicatore applicato all'AD dell'utilizzatore.
     */
    private static final double MOLTIPLICATORE = 1.5;

    /**
     * Attacchi base necessari prima di poter usare l'abilità.
     */
    private static final int RICARICA = 4;


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
        return RICARICA;
    }

    @Override
    public int usa(Avventuriero utilizzatore, Personaggio bersaglio) {
        int danno = 0;
        danno = (int) (utilizzatore.getAd() * Math.round(MOLTIPLICATORE));
        bersaglio.subisciDanno(danno);
        return danno;

    }
}
