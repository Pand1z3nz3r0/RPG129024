package it.unicam.cs.mpgc.rpg129024.model.gioco;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.TipoMostro;

/**
 * Mappa del dungeon: tipo di ogni casella, posizione dell'avventuriero e
 * posizione dei mostri.
 * <p>
 * L'avventuriero parte in basso a sinistra; la porta, in alto a destra, si
 * apre solo quando tutti i mostri sono stati sconfitti. I mostri sono
 * disposti per difficoltà crescente man mano che ci si avvicina alla porta.
 *
 * @author Davide Caliri
 */
public class Mappa {

    public static final int RIGHE = 10;
    public static final int COLONNE = 12;

    private static final int RIGA_PARTENZA = 9;
    private static final int COLONNA_PARTENZA = 0;
    private static final int RIGA_PORTA = 0;
    private static final int COLONNA_PORTA = 11;

    private final TipoCasella[][] caselle = new TipoCasella[RIGHE][COLONNE];
    private final Mostro[][] mostri = new Mostro[RIGHE][COLONNE];
    private int rigaEroe;
    private int colonnaEroe;
    private int mostriRimasti;

    /**
     * Crea la mappa della demo, con l'avventuriero nella casella di partenza.
     */
    public Mappa() {
        for (int riga = 0; riga < RIGHE; riga++) {
            for (int colonna = 0; colonna < COLONNE; colonna++) {
                caselle[riga][colonna] = TipoCasella.PAVIMENTO;
            }
        }
        caselle[RIGA_PORTA][COLONNA_PORTA] = TipoCasella.PORTA;

        // vicino alla partenza
        posiziona(TipoMostro.BANDITO, 9, 4);
        posiziona(TipoMostro.BANDITO, 7, 1);
        posiziona(TipoMostro.BANDITO, 8, 8);
        posiziona(TipoMostro.BANDITO, 5, 3);
        // a metà strada
        posiziona(TipoMostro.ZOMBIE, 6, 7);
        posiziona(TipoMostro.ZOMBIE, 4, 5);
        posiziona(TipoMostro.ZOMBIE, 3, 1);
        posiziona(TipoMostro.ZOMBIE, 7, 11);
        // vicino alla porta
        posiziona(TipoMostro.SCHELETRO, 2, 9);
        posiziona(TipoMostro.SCHELETRO, 1, 4);
        posiziona(TipoMostro.SCHELETRO, 0, 7);
        posiziona(TipoMostro.SCHELETRO, 1, 10);

        rigaEroe = RIGA_PARTENZA;
        colonnaEroe = COLONNA_PARTENZA;
    }

    private void posiziona(TipoMostro tipo, int riga, int colonna) {
        mostri[riga][colonna] = tipo.creaMostro();
        mostriRimasti++;
    }

    /**
     * Prova a spostare l'avventuriero nella direzione indicata.
     * <p>
     * Se la destinazione è la porta e ci sono ancora mostri, l'avventuriero
     * resta fermo.
     *
     * @param direzione la direzione scelta dal giocatore
     * @return cosa è successo
     */
    public EsitoMovimento muovi(Direzione direzione) {
        int nuovaRiga = rigaEroe + direzione.getDeltaRiga();
        int nuovaColonna = colonnaEroe + direzione.getDeltaColonna();

        if (!isDentro(nuovaRiga, nuovaColonna)
                || !caselle[nuovaRiga][nuovaColonna].isPercorribile()) {
            return EsitoMovimento.BLOCCATO;
        }
        boolean versoLaPorta = caselle[nuovaRiga][nuovaColonna] == TipoCasella.PORTA;
        if (versoLaPorta && !isPortaAperta()) {
            return EsitoMovimento.PORTA_CHIUSA;
        }

        rigaEroe = nuovaRiga;
        colonnaEroe = nuovaColonna;

        if (versoLaPorta) {
            return EsitoMovimento.PORTA;
        }
        if (mostri[rigaEroe][colonnaEroe] != null) {
            return EsitoMovimento.INCONTRO_MOSTRO;
        }
        return EsitoMovimento.MOSSO;
    }

    /**
     * @return il mostro nella casella dell'avventuriero, oppure {@code null}
     */
    public Mostro getMostroQui() {
        return mostri[rigaEroe][colonnaEroe];
    }

    /**
     * Rimuove il mostro sconfitto dalla casella dell'avventuriero.
     *
     * @throws IllegalStateException se nella casella non c'è nessun mostro
     */
    public void rimuoviMostroQui() {
        if (mostri[rigaEroe][colonnaEroe] == null) {
            throw new IllegalStateException("Nessun mostro in questa casella");
        }
        mostri[rigaEroe][colonnaEroe] = null;
        mostriRimasti--;
    }

    /**
     * @return il numero di mostri ancora vivi
     */
    public int getMostriRimasti() {
        return mostriRimasti;
    }

    /**
     * @return {@code true} se tutti i mostri sono stati sconfitti
     */
    public boolean isPortaAperta() {
        return mostriRimasti == 0;
    }

    /**
     * @return il tipo della casella indicata
     * @throws IllegalArgumentException se la casella è fuori dalla mappa
     */
    public TipoCasella getCasella(int riga, int colonna) {
        controllaDentro(riga, colonna);
        return caselle[riga][colonna];
    }

    /**
     * @return il mostro nella casella indicata, oppure {@code null}
     * @throws IllegalArgumentException se la casella è fuori dalla mappa
     */
    public Mostro getMostro(int riga, int colonna) {
        controllaDentro(riga, colonna);
        return mostri[riga][colonna];
    }

    public int getRigaEroe() {
        return rigaEroe;
    }

    public int getColonnaEroe() {
        return colonnaEroe;
    }

    private boolean isDentro(int riga, int colonna) {
        return riga >= 0 && riga < RIGHE && colonna >= 0 && colonna < COLONNE;
    }

    private void controllaDentro(int riga, int colonna) {
        if (!isDentro(riga, colonna)) {
            throw new IllegalArgumentException(
                    "Casella fuori dalla mappa: (" + riga + ", " + colonna + ")");
        }
    }
}