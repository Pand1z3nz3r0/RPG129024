package it.unicam.cs.mpgc.rpg129024.model.gioco;

/**
 * Risultato di un tentativo di movimento sulla mappa.
 *
 * @author Davide Caliri
 */
public enum EsitoMovimento {
    /**
     * L'avventuriero si è spostato su una casella libera.
     */
    MOSSO,
    /**
     * Lo spostamento non è possibile: bordo della mappa o muro.
     */
    BLOCCATO,
    /**
     * La porta è chiusa perché ci sono ancora mostri: l'avventuriero non si sposta.
     */
    PORTA_CHIUSA,
    /**
     * L'avventuriero è entrato nella casella di un mostro.
     */
    INCONTRO_MOSTRO,
    /**
     * L'avventuriero ha attraversato la porta aperta.
     */
    PORTA
}