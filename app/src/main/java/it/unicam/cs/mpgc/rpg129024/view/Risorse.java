package it.unicam.cs.mpgc.rpg129024.view;

import it.unicam.cs.mpgc.rpg129024.model.personaggi.Mostro;
import it.unicam.cs.mpgc.rpg129024.model.personaggi.TipoAvventuriero;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Accesso alle risorse dell'interfaccia: immagini, testi, foglio di stile e font.
 * <p>
 * Tutti i percorsi partono dalla radice della cartella {@code resources}
 * (ad esempio {@code /img/sfondo.png}). Le immagini ridimensionate mantengono
 * i pixel nitidi, come richiesto dallo stile pixel-art del gioco.
 *
 * @author Davide Caliri
 */
public final class Risorse {

    private static final String FOGLIO_DI_STILE = "/css/lumen.css";
    private static final String FONT = "/font/PressStart2P-Regular.ttf";
    private static final double DIMENSIONE_FONT = 12;

    private Risorse() {
        // classe di sole funzioni statiche: non si istanzia
    }

    /**
     * Carica un'immagine alla sua dimensione originale.
     *
     * @param percorso il percorso dell'immagine
     * @return l'immagine caricata
     * @throws IllegalStateException se l'immagine non è presente
     */
    public static Image immagine(String percorso) {
        return new Image(url(percorso).toExternalForm());
    }

    /**
     * Carica un'immagine ridimensionata, mantenendo le proporzioni e i pixel nitidi.
     *
     * @param percorso  il percorso dell'immagine
     * @param larghezza la larghezza desiderata
     * @param altezza   l'altezza desiderata
     * @return l'immagine caricata
     * @throws IllegalStateException se l'immagine non è presente
     */
    public static Image immagine(String percorso, double larghezza, double altezza) {
        return new Image(url(percorso).toExternalForm(), larghezza, altezza, true, false);
    }

    /**
     * Carica lo sprite di un tipo di avventuriero, ad esempio
     * {@code /img/barbaro.png} per {@link TipoAvventuriero#BARBARO}.
     *
     * @param tipo il tipo di avventuriero
     * @param lato il lato dell'immagine quadrata
     * @return lo sprite ridimensionato
     * @throws IllegalStateException se l'immagine non è presente
     */
    public static Image ritratto(TipoAvventuriero tipo, double lato) {
        return immagine("/img/" + tipo.name().toLowerCase(Locale.ROOT) + ".png", lato, lato);
    }

    /**
     * Carica lo sprite di un mostro, cercandolo per nome: ad esempio
     * {@code /img/zombie.png} per uno Zombie.
     *
     * @param mostro il mostro
     * @param lato   il lato dell'immagine quadrata
     * @return lo sprite ridimensionato
     * @throws IllegalStateException se l'immagine non è presente
     */
    public static Image ritratto(Mostro mostro, double lato) {
        return immagine("/img/" + mostro.getNome().toLowerCase(Locale.ROOT) + ".png", lato, lato);
    }

    /**
     * Legge un file di testo con codifica UTF-8.
     *
     * @param percorso il percorso del file
     * @return il contenuto del file
     * @throws IllegalStateException se il file non è presente
     * @throws UncheckedIOException  se il file non può essere letto
     */
    public static String testo(String percorso) {
        try (InputStream in = url(percorso).openStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Impossibile leggere " + percorso, e);
        }
    }

    /**
     * @return l'indirizzo del foglio di stile, da aggiungere alla scena
     * @throws IllegalStateException se il foglio di stile non è presente
     */
    public static String foglioDiStile() {
        return url(FOGLIO_DI_STILE).toExternalForm();
    }

    /**
     * Registra il font pixel del gioco, così da poterlo usare nel foglio di stile.
     *
     * @throws IllegalStateException se il font non è presente o non è valido
     * @throws UncheckedIOException  se il font non può essere letto
     */
    public static void caricaFont() {
        try (InputStream in = url(FONT).openStream()) {
            if (Font.loadFont(in, DIMENSIONE_FONT) == null) {
                throw new IllegalStateException("Font non valido: " + FONT);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Impossibile leggere " + FONT, e);
        }
    }

    private static URL url(String percorso) {
        URL url = Risorse.class.getResource(percorso);
        if (url == null) {
            throw new IllegalStateException("Risorsa non trovata: " + percorso);
        }
        return url;
    }
}
