package it.unicam.cs.mpgc.rpg129024.Model.Abilita;

public class PallaDiFuoco implements Abilita {
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
        return 4;
    }
}
