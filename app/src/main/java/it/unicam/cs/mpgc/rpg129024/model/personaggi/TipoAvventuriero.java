package it.unicam.cs.mpgc.rpg129024.model.personaggi;

import it.unicam.cs.mpgc.rpg129024.model.abilita.Abilita;
import it.unicam.cs.mpgc.rpg129024.model.abilita.AttaccoPotenziato;

import java.util.List;

/**
 * Catalogo dei tipi di avventuriero disponibili nel gioco.
 *
 * @author Davide Caliri
 */
public enum TipoAvventuriero {
    BARBARO("Barbaro",
            "Un guerriero delle terre selvagge, robusto e instancabile. "
                    + "Ha più vita di chiunque altro e, quando la rabbia lo travolge, "
                    + "scatena la sua Furia.",
            120, 14, true,
            new AttaccoPotenziato("Furia", "Colpo potente", 3, 2.0)
    ),
    MAGO("Mago",
            "Uno studioso delle arti arcane: fragile nel corpo, ma devastante nella magia. "
                    + "Ha meno vita del Barbaro, ma colpisce più forte, "
                    + "e la sua Palla di fuoco può ribaltare uno scontro.",
            90, 18, true,
            new AttaccoPotenziato("Palla di fuoco", "Lancia una palla di fuoco", 4, 2.5)
    ),
    TANK("Tank",
            "Si narra di un guardiano dall'armatura impenetrabile, "
                    + "capace di resistere a qualunque colpo… "
                    + "Nessuno sa ancora dove si trovi.",
            0, 0, false
    );
    private final String nome;
    private final String descrizione;
    private final int hp;
    private final int ad;
    private final boolean disponibile;
    private final List<Abilita> abilita;

    TipoAvventuriero(String nome, String descrizione, int hp, int ad,
                     boolean disponibile, Abilita... abilita) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.hp = hp;
        this.ad = ad;
        this.disponibile = disponibile;
        this.abilita = List.of(abilita);
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
     * @return le abilità dell'avventuriero
     */
    public List<Abilita> getAbilita() {
        return abilita;
    }

    /**
     * @return il nome dell'avventuriero
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return la descrizione dell'avventuriero
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    /**
     * @return il valore degli hp iniziale
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * @return il valore di attacco iniziale
     */
    public int getAd() {
        return this.ad;
    }

    /**
     * @return la disponibilità dell'avventuriero o meno
     */
    public boolean isDisponibile() {
        return this.disponibile;
    }
}