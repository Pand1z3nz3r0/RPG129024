package it.unicam.cs.mpgc.rpg129024.persistence;

/**
 * Errore durante il salvataggio o la lettura dei dati persistenti.
 * <p>
 * Nasconde i dettagli della tecnologia usata (file, JSON, database), così
 * chi usa un repository non dipende dalla sua implementazione.
 *
 * @author Davide Caliri
 */
public class PersistenzaException extends RuntimeException {

    /**
     * Costruisce una persistenza exception con un messaggio e una causa.
     * 
     * @param messaggio il messaggio di errore
     * @param causa     la causa dell'errore
     */
    public PersistenzaException(String messaggio, Throwable causa) {
        super(messaggio, causa);
    }
}