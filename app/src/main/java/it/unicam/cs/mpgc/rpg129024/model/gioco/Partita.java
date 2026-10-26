package it.unicam.cs.mpgc.rpg129024.model.gioco;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Avventuriero;

import java.util.Objects;
import java.util.Optional;

/**
 * Partita in corso.
 * <p>
 * Tiene insieme l'avventuriero, la mappa e l'eventuale battaglia in corso, e
 * applica le regole che collegano esplorazione e combattimento: l'incontro
 * con un mostro avvia una battaglia, la vittoria assegna una ricompensa, la
 * sconfitta o il raggiungimento della porta concludono la partita.
 * <p>
 * Durante una battaglia l'avventuriero non può muoversi.
 *
 * @author Davide Caliri
 */
public class Partita {

    /**
     * Aumento percentuale degli HP massimi dopo ogni vittoria.
     */
    private static final double BONUS_HP = 0.04;

    /**
     * Aumento percentuale dell'attacco dopo ogni vittoria.
     */
    private static final double BONUS_AD = 0.05;

    /**
     * Percentuale degli HP massimi recuperata dopo ogni vittoria.
     */
    private static final double CURA_VITTORIA = 0.25;

    private final Avventuriero avventuriero;
    private final Mappa mappa;
    private Battaglia battaglia;
    private int nemiciSconfitti;
    private EsitoPartita esito;

    /**
     * Crea una nuova partita con una mappa nuova.
     *
     * @param avventuriero l'avventuriero del giocatore
     * @throws NullPointerException se l'avventuriero è {@code null}
     */
    public Partita(Avventuriero avventuriero) {
        this.avventuriero = Objects.requireNonNull(avventuriero, "L'avventuriero non può essere null");
        this.mappa = new Mappa();
        this.battaglia = null;
        this.nemiciSconfitti = 0;
        this.esito = null;
    }

    /**
     * Muove l'avventuriero e applica le conseguenze del movimento:
     * l'incontro con un mostro avvia una battaglia, il raggiungimento della
     * porta conclude la partita.
     *
     * @param direzione la direzione scelta dal giocatore
     * @return l'esito del movimento
     * @throws IllegalStateException se la partita è finita o c'è una battaglia in corso
     */
    public EsitoMovimento muovi(Direzione direzione) {
        controllaInCorso();
        if (isInBattaglia()) {
            throw new IllegalStateException("Non ci si può muovere durante una battaglia");
        }
        EsitoMovimento esitoMovimento = mappa.muovi(direzione);
        switch (esitoMovimento) {
            case INCONTRO_MOSTRO -> battaglia = new Battaglia(avventuriero, mappa.getMostroQui());
            case PORTA -> esito = EsitoPartita.COMPLETATA;
            default -> {
            }
        }
        return esitoMovimento;
    }

    /**
     * Conclude la battaglia in corso, che deve essere finita.
     * <p>
     * In caso di vittoria l'avventuriero riceve la ricompensa, il mostro viene
     * rimosso dalla mappa e l'esplorazione può continuare. In caso di
     * sconfitta la partita termina.
     *
     * @return la ricompensa ottenuta, oppure un {@code Optional} vuoto in caso di sconfitta
     * @throws IllegalStateException se non c'è una battaglia o se non è ancora finita
     */
    public Optional<Ricompensa> concludiBattaglia() {
        if (!isInBattaglia()) {
            throw new IllegalStateException("Non c'è nessuna battaglia in corso");
        }
        if (!battaglia.isFinita()) {
            throw new IllegalStateException("La battaglia non è ancora finita");
        }

        boolean vittoria = battaglia.isVittoria();
        battaglia = null;

        if (!vittoria) {
            esito = EsitoPartita.SCONFITTA;
            return Optional.empty();
        }

        Ricompensa ricompensa = assegnaRicompensa();
        mappa.rimuoviMostroQui();
        nemiciSconfitti++;
        return Optional.of(ricompensa);
    }

    /**
     * Applica all'avventuriero i potenziamenti e la cura previsti dopo una
     * vittoria. La cura è calcolata sugli HP massimi già aumentati.
     */
    private Ricompensa assegnaRicompensa() {
        int bonusHpMax = (int) Math.ceil(avventuriero.getHpMax() * BONUS_HP);
        int bonusAd = (int) Math.ceil(avventuriero.getAd() * BONUS_AD);
        avventuriero.potenzia(bonusHpMax, bonusAd);

        int hpPrima = avventuriero.getHp();
        avventuriero.cura((int) Math.ceil(avventuriero.getHpMax() * CURA_VITTORIA));
        int hpCurati = avventuriero.getHp() - hpPrima;

        return new Ricompensa(bonusHpMax, bonusAd, hpCurati);
    }

    /**
     * Abbandona la partita, ad esempio quando il giocatore torna alla home.
     *
     * @throws IllegalStateException se la partita è già finita
     */
    public void abbandona() {
        controllaInCorso();
        battaglia = null;
        esito = EsitoPartita.ABBANDONATA;
    }

    private void controllaInCorso() {
        if (isFinita()) {
            throw new IllegalStateException("La partita è già finita");
        }
    }

    /**
     * @return {@code true} se la partita è conclusa, in qualunque modo
     */
    public boolean isFinita() {
        return esito != null;
    }

    /**
     * @return {@code true} se è in corso una battaglia
     */
    public boolean isInBattaglia() {
        return battaglia != null;
    }

    /**
     * @return la battaglia in corso, oppure {@code null} se non si sta combattendo
     */
    public Battaglia getBattaglia() {
        return battaglia;
    }

    /**
     * @return l'esito della partita, oppure {@code null} se è ancora in corso
     */
    public EsitoPartita getEsito() {
        return esito;
    }

    public Avventuriero getAvventuriero() {
        return avventuriero;
    }

    public Mappa getMappa() {
        return mappa;
    }

    public int getNemiciSconfitti() {
        return nemiciSconfitti;
    }
}