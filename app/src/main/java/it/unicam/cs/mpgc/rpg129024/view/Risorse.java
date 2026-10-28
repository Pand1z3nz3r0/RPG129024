package it.unicam.cs.mpgc.rpg129024.view;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

    /**
     *  Costruttore privato: la classe non può essere istanziata. Tutti i metodi
     *  sono statici.
     *  <p>
     *  @throws UnsupportedOperationException se si tenta di istanziare la classe
     *  </p>
     */
    private Risorse() {
        throw new UnsupportedOperationException("La classe non può essere istanziata");
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