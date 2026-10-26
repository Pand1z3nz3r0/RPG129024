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

    public PersistenzaException(String messaggio, Throwable causa) {
        super(messaggio, causa);
    }
}