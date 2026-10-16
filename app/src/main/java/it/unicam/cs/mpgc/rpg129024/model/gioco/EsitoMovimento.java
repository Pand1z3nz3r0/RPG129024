package it.unicam.cs.mpgc.rpg129024.model.gioco;

public enum EsitoMovimento {
    MOSSO("Mosso"),
    BLOCCATO("Bloccato"),
    INCONTRO_MOSTRO("Mostro"),
    PORTA("Porta");

    private String descrizione;

    private EsitoMovimento(String descrizione) {
        this.descrizione = descrizione;
    }
}
