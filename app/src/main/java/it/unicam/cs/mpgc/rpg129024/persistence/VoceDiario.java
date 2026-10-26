package it.unicam.cs.mpgc.rpg129024.persistence;

import it.unicam.cs.mpgc.rpg129024.model.gioco.EsitoPartita;
import it.unicam.cs.mpgc.rpg129024.model.gioco.Partita;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.TipoAvventuriero;

import java.util.Objects;

/**
 * Voce del diario delle avventure: il riassunto di una partita conclusa.
 *
 * @param nomeAvventuriero il nome scelto dal giocatore
 * @param tipo             il tipo di avventuriero
 * @param nemiciSconfitti  il numero di mostri sconfitti
 * @param esito            come si è conclusa la partita
 * @author Davide Caliri
 */
public record VoceDiario(String nomeAvventuriero,
                         TipoAvventuriero tipo,
                         int nemiciSconfitti,
                         EsitoPartita esito) {

    /**
     * Controlla la validità dei dati.
     *
     * @throws NullPointerException     se un valore obbligatorio è {@code null}
     * @throws IllegalArgumentException se il numero di nemici è negativo
     */
    public VoceDiario {
        Objects.requireNonNull(nomeAvventuriero, "Il nome non può essere null");
        Objects.requireNonNull(tipo, "Il tipo non può essere null");
        Objects.requireNonNull(esito, "L'esito non può essere null");
        if (nemiciSconfitti < 0) {
            throw new IllegalArgumentException("I nemici sconfitti non possono essere negativi");
        }
    }

    /**
     * Crea la voce che riassume una partita conclusa.
     *
     * @param partita la partita da registrare
     * @return la voce del diario
     * @throws IllegalStateException se la partita non è ancora finita
     */
    public static VoceDiario daPartita(Partita partita) {
        if (!partita.isFinita()) {
            throw new IllegalStateException("Si possono registrare solo partite concluse");
        }
        return new VoceDiario(
                partita.getAvventuriero().getNome(),
                partita.getAvventuriero().getTipo(),
                partita.getNemiciSconfitti(),
                partita.getEsito());
    }
}