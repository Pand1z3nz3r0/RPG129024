package it.unicam.cs.mpgc.rpg129024.model.abilita;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Personaggio;

/**
 * Abilità speciale che un avventuriero può usare in battaglia al posto
 * dell'attacco base.
 * <p>
 * Ogni abilità definisce il proprio effetto e il numero di attacchi base
 * necessari prima di poterla usare. Il conteggio degli attacchi è gestito
 * dalla battaglia, perciò le implementazioni non devono mantenere uno stato
 * che cambia durante il gioco: la stessa istanza può essere condivisa da più
 * avventurieri.
 * <p>
 * Per aggiungere una nuova abilità è sufficiente implementare questa
 * interfaccia e associarla a un {@code TipoAvventuriero}.
 *
 * @author Davide Caliri
 */
public interface Abilita {

    /**
     * Restituisce il nome dell'abilità, mostrato nell'interfaccia e nel
     * resoconto della battaglia.
     *
     * @return il nome dell'abilità
     */
    public String getNome();

    /**
     * Restituisce una breve descrizione dell'effetto dell'abilità.
     *
     * @return la descrizione dell'abilità
     */
    public String getDescrizione();

    /**
     * Restituisce il numero di attacchi base che l'avventuriero deve
     * eseguire, all'interno della stessa battaglia, prima di poter usare
     * l'abilità.
     *
     * @return il numero di attacchi base necessari, maggiore di zero
     */
    public int getRicarica();


    /**
     * Applica l'effetto dell'abilità.
     *
     * @param utilizzatore l'avventuriero che usa l'abilità
     * @param bersaglio    il personaggio che la subisce
     * @return i danni inflitti (0 se l'abilità non fa danni)
     */
    int usa(Avventuriero utilizzatore, Personaggio bersaglio);
}
