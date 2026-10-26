package it.unicam.cs.mpgc.rpg129024.persistence;

import java.util.List;

/**
 * Archivio delle voci del diario delle avventure.
 * <p>
 * Il resto dell'applicazione dipende solo da questa interfaccia: per
 * cambiare il modo in cui il diario viene salvato (file JSON, database, ...)
 * basta fornirne una nuova implementazione.
 *
 * @author Davide Caliri
 */
public interface DiarioRepository {

    /**
     * Aggiunge una voce al diario.
     *
     * @param voce la voce da salvare
     * @throws PersistenzaException se il salvataggio non riesce
     */
    void salva(VoceDiario voce);

    /**
     * Restituisce tutte le voci del diario, dalla più vecchia alla più recente.
     *
     * @return le voci salvate; una lista vuota se il diario è vuoto
     * @throws PersistenzaException se la lettura non riesce
     */
    List<VoceDiario> caricaTutte();
}