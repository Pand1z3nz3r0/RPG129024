package it.unicam.cs.mpgc.rpg129024.model.abilita;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Personaggio;

import java.util.Objects;

/**
 * Abilità che infligge danni pari all'AD dell'utilizzatore moltiplicato per
 * un valore fisso, arrotondati all'intero più vicino.
 * <p>
 * Nome, descrizione, ricarica e moltiplicatore sono definiti nel catalogo
 * {@code TipoAvventuriero}: una nuova abilità di questo tipo non richiede
 * una nuova classe.
 *
 * @author Davide Caliri
 */
public class AttaccoPotenziato implements Abilita {
    private final String nome;
    private final String descrizione;
    private final int ricarica;
    private final double moltiplicatore;

    /**
     * Crea un attacco potenziato.
     *
     * @param nome           il nome dell'abilità
     * @param descrizione    una breve descrizione dell'effetto
     * @param ricarica       gli attacchi base necessari prima di poterla usare, maggiore di zero
     * @param moltiplicatore il moltiplicatore applicato all'AD, maggiore di zero
     * @throws NullPointerException     se nome o descrizione sono {@code null}
     * @throws IllegalArgumentException se ricarica o moltiplicatore non sono positivi
     */
    public AttaccoPotenziato(String nome, String descrizione, int ricarica, double moltiplicatore) {
        if (ricarica <= 0 || moltiplicatore <= 0) {
            throw new IllegalArgumentException("Ricarica e moltiplicatore devono essere positivi");
        }
        this.nome = Objects.requireNonNull(nome, "Il nome non può essere null");
        this.descrizione = Objects.requireNonNull(descrizione, "La descrizione non può essere null");
        this.ricarica = ricarica;
        this.moltiplicatore = moltiplicatore;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public int getRicarica() {
        return ricarica;
    }


    @Override
    public int usa(Avventuriero utilizzatore, Personaggio bersaglio) {
        int danno = (int) Math.round(utilizzatore.getAd() * moltiplicatore);
        bersaglio.subisciDanno(danno);
        return danno;
    }
}
