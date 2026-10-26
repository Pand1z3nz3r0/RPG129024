package it.unicam.cs.mpgc.rpg129024.model.gioco;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Mostro;

import java.util.Objects;

/**
 * Scontro a turni tra un avventuriero e un mostro.
 * <p>
 * A ogni turno il giocatore sceglie se eseguire un attacco base o usare
 * l'abilità speciale; se il mostro sopravvive, contrattacca subito.
 * L'abilità diventa disponibile dopo un numero di attacchi base pari alla
 * sua ricarica e resta disponibile finché non viene usata.
 * <p>
 * La battaglia non si occupa delle ricompense: stabilisce soltanto quando
 * lo scontro è finito e chi lo ha vinto.
 *
 * @author Davide Caliri
 */
public class Battaglia {

    private final Avventuriero avventuriero;
    private final Mostro mostro;
    private int turno;
    private int attacchiMancanti;

    /**
     * Crea una nuova battaglia. L'abilità parte scarica.
     *
     * @param avventuriero l'avventuriero che combatte
     * @param mostro       il mostro da affrontare
     * @throws NullPointerException se uno dei due è {@code null}
     */
    public Battaglia(Avventuriero avventuriero, Mostro mostro) {
        this.avventuriero = Objects.requireNonNull(avventuriero, "L'avventuriero non può essere null");
        this.mostro = Objects.requireNonNull(mostro, "Il mostro non può essere null");
        this.turno = 0;
        this.attacchiMancanti = avventuriero.getAbilita().getRicarica();
    }

    /**
     * Esegue un turno in cui l'avventuriero usa l'attacco base.
     *
     * @return il resoconto del turno
     * @throws IllegalStateException se la battaglia è già finita
     */
    public EsitoTurno attaccoBase() {
        controllaInCorso();
        int dannoInflitto = avventuriero.attacca(mostro);
        if (attacchiMancanti > 0) {
            attacchiMancanti--;
        }
        return concludiTurno(false, dannoInflitto);
    }

    /**
     * Esegue un turno in cui l'avventuriero usa la sua abilità speciale.
     * Dopo l'uso l'abilità torna scarica.
     *
     * @return il resoconto del turno
     * @throws IllegalStateException se la battaglia è finita o l'abilità non è pronta
     */
    public EsitoTurno usaAbilita() {
        controllaInCorso();
        if (!isAbilitaPronta()) {
            throw new IllegalStateException("L'abilità non è ancora pronta");
        }
        int dannoInflitto = avventuriero.getAbilita().usa(avventuriero, mostro);
        attacchiMancanti = avventuriero.getAbilita().getRicarica();
        return concludiTurno(true, dannoInflitto);
    }

    /**
     * Completa il turno con il contrattacco del mostro, se è ancora vivo.
     *
     * @param abilitaUsata  se nel turno è stata usata l'abilità speciale
     * @param dannoInflitto i danni inflitti dall'avventuriero
     * @return il resoconto del turno
     */
    private EsitoTurno concludiTurno(boolean abilitaUsata, int dannoInflitto) {
        turno++;
        int dannoSubito = 0;
        if (mostro.isVivo()) {
            dannoSubito = mostro.attacca(avventuriero);
        }
        return new EsitoTurno(turno, abilitaUsata, dannoInflitto, dannoSubito);
    }

    /**
     * @throws IllegalStateException se la battaglia è già finita
     */
    private void controllaInCorso() {
        if (isFinita()) {
            throw new IllegalStateException("La battaglia è già finita");
        }
    }

    /**
     * @return {@code true} se l'abilità speciale può essere usata in questo turno
     */
    public boolean isAbilitaPronta() {
        return attacchiMancanti == 0;
    }

    /**
     * @return gli attacchi base ancora necessari prima che l'abilità sia pronta
     */
    public int getAttacchiMancanti() {
        return attacchiMancanti;
    }

    /**
     * @return {@code true} se uno dei due combattenti è stato sconfitto
     */
    public boolean isFinita() {
        return !avventuriero.isVivo() || !mostro.isVivo();
    }

    /**
     * @return {@code true} se il mostro è stato sconfitto
     */
    public boolean isVittoria() {
        return !mostro.isVivo();
    }

    /**
     * @return il numero di turni giocati
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @return l'avventuriero che combatte
     */
    public Avventuriero getAvventuriero() {
        return avventuriero;
    }

    /**
     * @return il mostro affrontato
     */
    public Mostro getMostro() {
        return mostro;
    }
}