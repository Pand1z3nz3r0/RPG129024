package it.unicam.cs.mpgc.rpg129024.model.gioco;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.Mostro;

public class Battaglia {
    private static final double BONUS_HP = 0.08;
    private static final double BONUS_AD = 0.10;
    private static final double CURA_VITTORIA = 0.25;

    private Avventuriero avventuriero;
    private Mostro mostro;
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
        if (avventuriero == null) {
            throw new NullPointerException("Avventuriero non può essere nullo");
        }
        if (mostro == null) {
            throw new NullPointerException("Mostro non può essere nullo");
        }
        this.avventuriero = avventuriero;
        this.mostro = mostro;
        this.turno = 0;
        this.attacchiMancanti = this.avventuriero.getTipo().getAbilita().getRicarica();
    }


    /**
     * Esegue un turno in cui l'avventuriero usa l'attacco base.
     *
     * @return il resoconto del turno
     * @throws IllegalStateException se la battaglia è già finita
     */
    public EsitoTurno attaccoBase() {
        controllaInCorso();
        int dannoInflitto = this.avventuriero.attacca(this.mostro);
        if (this.attacchiMancanti > 0) {
            this.attacchiMancanti--;
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
        int dannoInflitto = this.avventuriero.getAbilita().usa(this.avventuriero, this.mostro);
        this.attacchiMancanti = this.avventuriero.getAbilita().getRicarica();
        return concludiTurno(true, dannoInflitto);
    }

    /**
     * Completa il turno con il contrattacco del mostro, se è ancora vivo.
     */
    private EsitoTurno concludiTurno(boolean abilitaUsata, int dannoInflitto) {
        this.turno++;
        int dannoSubito = 0;
        if (this.mostro.isVivo()) {
            dannoSubito = this.mostro.attacca(this.avventuriero);
        }
        return new EsitoTurno(this.turno, abilitaUsata, dannoInflitto, dannoSubito);
    }

    private void controllaInCorso() {
        if (isFinita()) {
            throw new IllegalStateException("La battaglia è già finita");
        }
    }

    /**
     * @return {@code true} se l'abilità speciale può essere usata in questo turno
     */
    public boolean isAbilitaPronta() {
        return this.attacchiMancanti == 0;
    }

    /**
     * @return gli attacchi base ancora necessari prima che l'abilità sia pronta
     */
    public int getAttacchiMancanti() {
        return this.attacchiMancanti;
    }

    private boolean isFinita() {
        return !this.avventuriero.isVivo() || !this.mostro.isVivo();
    }

    public boolean isVittoria() {
        return !this.mostro.isVivo();
    }

    public Avventuriero getAvventuriero() {
        return avventuriero;
    }

    public Mostro getMostro() {
        return mostro;
    }

    public void setPotenziamnetiVittoria() {
        double powerUpAd = this.avventuriero.getAd();
        double powerUpHp = this.avventuriero.getHpMax();
        double recuperoHp = this.avventuriero.getHp();

        powerUpAd = Math.ceil(powerUpAd * BONUS_AD / 100);
        powerUpHp = Math.ceil(powerUpHp * BONUS_HP / 100);
        recuperoHp = Math.ceil(recuperoHp * CURA_VITTORIA / 100);

        this.avventuriero.potenzia((int) powerUpHp, (int) powerUpAd);
        this.avventuriero.cura((int) recuperoHp);
    }
}
