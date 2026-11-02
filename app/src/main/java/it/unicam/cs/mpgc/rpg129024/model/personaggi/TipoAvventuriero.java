package it.unicam.cs.mpgc.rpg129024.model.personaggi;

import it.unicam.cs.mpgc.rpg129024.model.abilita.Abilita;
import it.unicam.cs.mpgc.rpg129024.model.abilita.Furia;
import it.unicam.cs.mpgc.rpg129024.model.abilita.PallaDiFuoco;

/**
 * Catalogo dei tipi di avventuriero disponibili nel gioco.
 * <p>
 * Ogni tipo definisce le statistiche iniziali, l'abilità speciale e la
 * descrizione mostrata nella schermata di scelta. Per aggiungere un nuovo
 * tipo di avventuriero è sufficiente aggiungere una costante.
 *
 * @author Davide Caliri
 */
public enum TipoAvventuriero {

    BARBARO("Barbaro",
            "Un guerriero delle terre selvagge, robusto e instancabile. "
                    + "Ha più vita di chiunque altro e, quando la rabbia lo travolge, "
                    + "scatena la sua Furia.",
            120, 14, new Furia(), true),

    MAGO("Mago",
            "Uno studioso delle arti arcane: fragile nel corpo, ma devastante nella magia. "
                    + "Ha meno vita del Barbaro, ma colpisce più forte, "
                    + "e la sua Palla di fuoco può ribaltare uno scontro.",
            90, 18, new PallaDiFuoco(), true),

    TANK("Tank",
            "Si narra di un guardiano dall'armatura impenetrabile, "
                    + "capace di resistere a qualunque colpo… "
                    + "Nessuno sa ancora dove si trovi.",
            0, 0, null, false);

    private final String nome;
    private final String descrizione;
    private final int hp;
    private final int ad;
    private final Abilita abilita;
    private final boolean disponibile;

    TipoAvventuriero(String nome, String descrizione, int hp, int ad,
                     Abilita abilita, boolean disponibile) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.hp = hp;
        this.ad = ad;
        this.abilita = abilita;
        this.disponibile = disponibile;
    }

    /**
     * Crea un nuovo avventuriero di questo tipo.
     *
     * @param nomeGiocatore il nome scelto dal giocatore
     * @return un nuovo avventuriero con le statistiche iniziali di questo tipo
     * @throws IllegalStateException    se il tipo non è ancora disponibile
     * @throws IllegalArgumentException se il nome non è valido
     */
    public Avventuriero creaAvventuriero(String nomeGiocatore) {
        if (!this.disponibile) {
            throw new IllegalStateException(this.nome + " non è ancora disponibile");
        }
        return new Avventuriero(nomeGiocatore, this);
    }

    /**
     * @return il nome del tipo di avventuriero
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return la descrzione mostrata nella schermata di scelta
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @return i punti vita iniziali
     */
    public int getHp() {
        return hp;
    }

    /**
     * @return il valore di attacco iniziale
     */
    public int getAd() {
        return ad;
    }

    /**
     * @return l'abilità speciale, oppure {@code null} se il tipo non è disponibile
     */
    public Abilita getAbilita() {
        return abilita;
    }

    /**
     * @return {@code true} se il tipo può essere scelto dal giocatore
     */
    public boolean isDisponibile() {
        return disponibile;
    }
}